grammar Smoola;
@header { 
    import ast.*;
    import ast.node.*;
    import ast.node.expression.*;
    import ast.node.expression.Value.*;
    import ast.node.statement.*;
    import ast.node.declaration.*;
    import ast.Type.*;
    import ast.Type.ArrayType.*;
    import ast.Type.UserDefinedType.*;
    import ast.Type.PrimitiveType.*;
    import java.util.ArrayList;
}

    program:
        {Program prog = new Program();
	    //VisitorImpl v = new VisitorImpl();
        //prog.accept(v);
        } mainClass[prog] (classDeclaration[prog])* EOF 
    ;

    mainClass [Program prog] returns [ClassDeclaration main]:
        'class' className = ID {
            Identifier id = new Identifier($className.text);
            $main = new ClassDeclaration(id, null);
        }
        '{' 'def' methodname = ID '(' ')' ':' 'int' 
        {
            Identifier mid = new Identifier($methodname.text);
            MethodDeclaration mainMethodDec = new MethodDeclaration(mid);
            IntType t = new IntType(); mainMethodDec.setReturnType(t);

            }
        '{'  stms = statements{
            for (int i = 0; i < ($stms.multipleStatements).size(); i++) {
                mainMethodDec.addStatement($stms.multipleStatements.get(i));
		    }
         }
         'return' retexp = expression{  
            mainMethodDec.setReturnValue($retexp.expr);
            $main.addMethodDeclaration(mainMethodDec);
            $prog.setMainClass($main);
            }
            ';' '}' '}' 
         ;

    classDeclaration [Program prog] returns [ClassDeclaration classDec]:
        'class' classname = ID ('extends' parentname = ID)?
        {
            Identifier classid = new Identifier($classname.text);
            Identifier parentclassid = new Identifier($parentname.text);
            ClassDeclaration classDecObj = new ClassDeclaration(classid, parentclassid);
        }
        '{' (vardec = varDeclaration {classDecObj.addVarDeclaration($vardec.varDec);})* 
            (methoddec = methodDeclaration {classDecObj.addMethodDeclaration($methoddec.methodDec);})* '}'
            {   
            $classDec = classDecObj; 
            $prog.addClass(classDecObj);
            } 
    ;
    varDeclaration returns [VarDeclaration varDec]:
        'var' name = ID ':' t = type ';' 
        {
            Identifier id = new Identifier($name.text);
            $varDec = new VarDeclaration(id, $t.t);
        }
    ;
    methodDeclaration returns[MethodDeclaration methodDec]:
        'def' methodname = ID{
            Identifier id = new Identifier($methodname.text);
            $methodDec = new MethodDeclaration(id);
        }
        ('(' ')' | ('(' id = ID ':' tp = type {   
            Identifier vardecid = new Identifier($id.text);
            VarDeclaration arg = new VarDeclaration(vardecid, $tp.t);
            $methodDec.addArg(arg);}
        (',' id = ID ':' tp = type
        {
            Identifier vardecid2 = new Identifier($id.text);
            VarDeclaration arg2 = new VarDeclaration(vardecid2, $tp.t);
            $methodDec.addArg(arg2);
        })* ')')) ':' 
        rettype = type '{' { $methodDec.setReturnType($rettype.t); }
        (vardec = varDeclaration { $methodDec.addLocalVar($vardec.varDec); })*
        stms = statements {
            for(int i = 0; i < $stms.multipleStatements.size(); i++){
                $methodDec.addStatement($stms.multipleStatements.get(i));
            }
        }
        'return' retvalexpr = expression {$methodDec.setReturnValue($retvalexpr.expr);}';' '}'
 
    ;
    statements returns [ArrayList<Statement> multipleStatements]:
        {$multipleStatements = new ArrayList<>();}
        (stm = statement{$multipleStatements.add($stm.stm);})*
    ;
    statement returns [Statement stm]:
        st = statementBlock {$stm = $st.block;} |
        st1 = statementCondition {$stm = $st1.conditional;} |
        st2 = statementLoop {$stm = $st2.wh;} |
        st3 = statementWrite {$stm = $st3.stm_write;} |
        st4 = statementAssignment {$stm = ($st4.assign);}
      
    ;
    statementBlock returns [Block block]:
        '{' {$block = new Block();}
        stms = statements {
            for(int i = 0 ; i < $stms.multipleStatements.size(); i ++ ){
                $block.addStatement($stms.multipleStatements.get(i));
            }
        } 
        '}' 
    ;
    statementCondition returns [Conditional conditional]:
        'if' '('exp = expression')' 'then' 
        cst = statement {Conditional cond = new Conditional($exp.expr, $cst.stm);}
        ('else' ast = statement {cond.setAlternativeBody($ast.stm);})?
        {$conditional = cond;}
      
    ;
    statementLoop returns [While wh]:
        'while' '(' exp = expression ')' st = statement {
            $wh = new While($exp.expr, $st.stm);
        }
    ;
    statementWrite returns [Write stm_write]:
        'writeln(' expr = expression ')' ';' {$stm_write = new Write($expr.expr);} 
    ;
    statementAssignment returns [Assign assign]:
        expr = expression ';'
        {
            if($expr.lvalue != null && $expr.rvalue != null){

                $assign = new Assign($expr.lvalue, $expr.rvalue);
            }
        }
    ;

    expression returns [Expression lvalue, Expression rvalue, Expression expr]:
		retval = expressionAssignment {
            $rvalue = $retval.rvalue;
            $lvalue = $retval.lvalue;
            $expr = $retval.expr;
        }
	;

    expressionAssignment returns [Expression lvalue, Expression rvalue, Expression expr]:
		 expr_lvalue = expressionOr '=' expr_rvalue = expressionAssignment {
             $lvalue = $expr_lvalue.expr; $rvalue = $expr_rvalue.expr; 
             BinaryOperator bo = BinaryOperator.assign;
             BinaryExpression be = new BinaryExpression($expr_lvalue.expr, $expr_rvalue.expr, bo);
             $expr = be;
         }
         | exp = expressionOr {$expr = $exp.expr; $rvalue = null; $lvalue = null;}
	;

    expressionOr returns [Expression expr, BinaryOperator be]:
		lvalue = expressionAnd rvalue = expressionOrTemp{
            if($rvalue.expr != null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
            }
            else{
                $expr = $lvalue.expr;
            }
        }
	;

    expressionOrTemp returns [Expression expr, BinaryOperator bo]:
		'||'{$bo = BinaryOperator.or;} lvalue = expressionAnd rvalue = expressionOrTemp
        {$expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);}
	    |
	;

    expressionAnd returns [Expression expr]:
		lvalue = expressionEq rvalue = expressionAndTemp
        {
            if($rvalue.expr != null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
        }
            else{
                $expr = $lvalue.expr;
            }
        }
	;

    expressionAndTemp returns [Expression expr, BinaryOperator bo]:
		'&&'{$bo = BinaryOperator.and;} expr1 = expressionEq expr2 = expressionAndTemp
        {$expr = new BinaryExpression($expr1.expr, $expr2.expr, $expr2.bo);}
	    |
	;

    expressionEq returns [Expression expr]:
		lvalue = expressionCmp rvalue = expressionEqTemp {
            if($rvalue.expr != null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
        }
            else{
                $expr = $lvalue.expr;
            }
        }
	;

    expressionEqTemp returns [Expression expr, BinaryOperator bo]:
		('=='{$bo = BinaryOperator.eq;} | '<>' {$bo = BinaryOperator.neq;}) 
        expr1 = expressionCmp expr2 = expressionEqTemp 
        {$expr = new BinaryExpression($expr1.expr, $expr2.expr, $expr2.bo);}
	    |
	;

    expressionCmp returns [Expression expr]:
		lvalue = expressionAdd rvalue = expressionCmpTemp{
            if($rvalue.expr != null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
        }
            else{
                $expr = $lvalue.expr;
            }
        }
	;

    expressionCmpTemp returns [Expression expr, BinaryOperator bo]:
		('<' {$bo = BinaryOperator.lt;} | '>' {$bo = BinaryOperator.gt;}) 
        expr1 = expressionAdd expr2 = expressionCmpTemp 
        {$expr = new BinaryExpression($expr1.expr, $expr2.expr, $expr2.bo);}
	    |
	;

    expressionAdd returns [Expression expr]:
		lvalue = expressionMult rvalue = expressionAddTemp{
            if($rvalue.expr != null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
        }
            else{
                $expr = $lvalue.expr;
            }
        }
	;

    expressionAddTemp returns [Expression expr, BinaryOperator bo]:
		('+' { $bo = BinaryOperator.add; } | '-' { $bo = BinaryOperator.sub; } ) 
        expr1 = expressionMult expr2 =  expressionAddTemp
        {$expr = new BinaryExpression($expr1.expr, $expr2.expr, $expr2.bo);}
	    |
	;

        expressionMult returns [Expression expr]:
		lvalue = expressionUnary rvalue = expressionMultTemp{
            if($rvalue.expr != null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
            }
            else{ $expr = $lvalue.expr;}
        }
	;

    expressionMultTemp returns [Expression expr, BinaryOperator bo]:
		('*' {$bo = BinaryOperator.mult;} | '/' {$bo = BinaryOperator.div;} ) 
        expr1 = expressionUnary expr2 = expressionMultTemp
        {$expr = new BinaryExpression($expr1.expr, $expr2.expr, $expr2.bo);}
	    |
	;

    expressionUnary returns [Expression expr]:
		 ('!'  exp = expressionUnary) {UnaryOperator uo = UnaryOperator.not; $expr = new UnaryExpression(uo, $exp.expr);}
         | ('-'  exp1 = expressionUnary) {UnaryOperator uo1 = UnaryOperator.minus; $expr = new UnaryExpression(uo1, $exp1.expr);} 
         |	exp2 = expressionMem {$expr = $exp2.expr;}
	;

    expressionMem returns [Expression expr]:
		instance = expressionMethods index = expressionMemTemp
        {$expr = new ArrayCall($instance.expr, $index.expr);}
	;

    expressionMemTemp returns [Expression expr]:
		'[' index = expression ']' {$expr = $index.expr;}
	    |
	;
	expressionMethods returns [Expression expr]: // not sure
	    instance = expressionOther methodcall = expressionMethodsTemp[$instance.expr] {$expr = $methodcall.methodcall;}
	;
	expressionMethodsTemp [Expression instance] returns [MethodCall methodcall]:
	    '.'  ((methodname = ID '(' ')'{   
                Identifier id = new Identifier($methodname.text);
                $methodcall = new MethodCall(instance, id);
            }) 
        | methodname = ID '(' {
                Identifier id = new Identifier($methodname.text);
                $methodcall = new MethodCall(instance, id);
            }
        (arg = expression {$methodcall.addArg($arg.expr);} (',' arg = expression {$methodcall.addArg($arg.expr);})*) ')' 
        | 'length' {Length len = new Length(instance); }) 
        expressionMethodsTemp[$instance]
	    |
	; // incomplete

    expressionOther returns [Expression expr, Expression lvalue, Expression rvalue]:

		num = CONST_NUM{   
                IntType t = new IntType();
                Expression temp_expr = new IntValue($num.int, t);
                $expr = temp_expr;
            }
        |	str = CONST_STR {
            StringType st = new StringType();
            $expr = new StringValue($str.text, st);
        }
            
        | 'new ' 'int' '[' num = CONST_NUM ']'
            {   
                IntType t = new IntType();
                IntValue val = new IntValue($num.int, t);
                NewArray newarr = new NewArray();
                newarr.setExpression(val);
                $expr = newarr;
            }
        |   'new ' name = ID '(' ')' {
            Identifier id = new Identifier($name.text);
            $expr = new NewClass(id);
            }
        |   'this' { $expr = new This();}
        |   constval = 'true' {
                                BooleanType bt = new BooleanType(); 
                                $expr = new BooleanValue(true, bt); 
                             }
        |   constval = 'false'{ BooleanType bt = new BooleanType();
                                $expr = new BooleanValue(false, bt);
                                }
        |	id = ID {$expr = new Identifier($id.text);}
        |   id = ID '[' exp = expression ']' 
            {     
                Identifier identifier = new Identifier($id.text);
                $expr = new ArrayCall(identifier, $exp.expr);
            }
        |	'(' thisexpr = expression ')' {
                $lvalue = $thisexpr.lvalue;
                $rvalue = $thisexpr.rvalue;
                $expr = $thisexpr.expr;
            }
	;

	type returns [Type t]:
	    'int' {$t = new IntType();}|
	    'boolean' {$t = new BooleanType();}|
	    'string' {$t = new StringType();}|
	    'int' '[' ']' {$t = new ArrayType();}|
	    ID {$t = new UserDefinedType();}
	;

    CONST_NUM:
		[0-9]+ 
	;

    CONST_STR:
		('"') (~('\r' | '\n' | '"')*) ('"') 
	;
    NL:
		'\r'? '\n' -> skip
	;

    ID:
		[a-zA-Z_][a-zA-Z0-9_]*
	;

    COMMENT:
		'#'(~[\r\n])* -> skip
	;

    WS:
    	[ \t] -> skip
    ;
