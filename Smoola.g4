grammar Smoola;
@header { 
    import ast.Type.*;
    import ast.node.Program;
    import ast.node.Declaration.*;
    import ast.node.expression.*;
    import ast.node.type.*;
}

    program:
        {Program prog = new Program();} mainClass[prog] (classDeclaration[prog])* EOF 
    ;
    mainClass[Program Prog] returns [ClassDeclaration classDec]:
        'class' className = ID {
            Identifier id = new Identifier($className.text);
            $classDec = new classDeclaration(id, Null);
            MainMethodDeclaration mainMethodDec = new MainMethodDeclaration();
        }
        '{' 'def' methodname = ID '(' ')' ':' 'int' '{'  stms = statements{
            for (int i = 0; i < ($stms.multipleStatements).size(); i++) {
                mainMethodDec.addStatement($stms.multipleStatements.get(i));
		    }
         }
         'return' retexp = expression  
         {  mainMethodDec.setReturnValue($retexp.expr);
            mainClass.addMethodDeclaration(mainMethodDec);
            $prog.setMainClass(mainClass);}';' '}' '}' // $prog or prog?
         ;

    classDeclaration[Program prog] returns [ClassDeclaration classDec]:
        'class' classname = ID ('extends' parentname = ID)?
        {
            Identifier classid = new Identifier($classname.text);
            Identifier parentclassid = new Identifier($parentname.text);
            ClassDeclaration classDecObj = new ClassDeclaration(classid, parentclassid);
        }
        '{' (vardec = varDeclaration {classDecObj.addVarDeclaration($vardec.varDec);})* 
            (methoddec = methodDeclaration {classDecObj.addMethodDeclaration($methoddec.methodDec);})* '}'
        {$classDec = classDecObj; $prog.addClass(classDecObj);} 
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
            methodDeclaration = new MethodDeclaration(id);
        }
        ('(' ')' | ('(' id = ID ':' tp = type {   
            Identifier vardecid = new Identifier($id.text);
            VarDeclaration arg = new VarDeclaration(vardecid, $tp.t);
            methodDeclaration.addArg(arg);}
        (',' id = ID ':' tp = type
        {
            Identifier vardecid = new Identifier($id.text);
            VarDeclaration arg = new VarDeclaration(vardecid, $tp.t);
            methodDeclaration.addArg(arg);
        })* ')')) ':' 
        rettype = type '{' { methodDeclaration.setReturnValue($rettype.t); }
        (vardec = varDeclaration { methodDeclaration.addLocalVar($vardec.varDec); })*
        stms = statements {
            for(int i = 0; i < $stms.multipleStatements.size(); i++){
                methodDeclaration.addStatement($stms.multipleStatements.get(i));
            }
        }
        'return' retvalexpr = expression {methodDeclaration.setReturnValue($retvalexpr.expr);}';' '}'
        {$methodDec = methodDeclaration;}
 
    ;
    statements returns [ArrayList<Statement> multipleStatements]:
        {$multipleStatements = new ArrayList<>();}
        (stm = statement{$multipleStatements.add($stm.stm);})*
    ;
    statement returns[Statement stm]:
        st = statementBlock {$stm = $st.block;} |
        st = statementCondition {$stm = $st.condition;} |
        st = statementLoop {$stm = $st.while;} |
        st = statementWrite {$stm = $st.stm_write;} |
        st = statementAssignment {$stm = ($st.stm);}
      
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
        'if' '('expr = expression')' 'then' cst = statement {Conditional cond = new Conditional($expr.expr, $cst.stm;);}('else' ast = statement {cond.setAlternativeBody($ast.stm);})?
        {$conditional = cond;}
      
    ;
    statementLoop returns [While while]:
        'while' '(' expr = expression ')' st = statement {
            $while = new While($expr.expr, $st.stm);
        }
    ;
    statementWrite returns [Write stm_write]:
        'writeln(' expr = expression ')' ';' {$stm_write = new Write($expr.expr);} 
    ;
    statementAssignment returns [Assign assign]:
        expr = expression ';'
        {
            // Expression lvalue = .... 
            // Expression rvalue = ....
            // $assign = new Assign(lvaue, rvalue);
        }
    ;

    expression returns [Expression lvalue, Expression rvalue, Expression expr]:
		retval = expressionAssignment {
            rval = $retval.rvalue;
            lval = $retval.lvalue;
            exp = $retval.expr;
            if($lvalue == Null && $rvalue == Null){
                $expr = exp.expr;
                $lvalue = Null;
                $rvalue = Null;
                }
            else{
                $lvalue = lval.expr;
                $rvalue = rval.expr;
                $expr = exp.expr;
            }
        }
	;

    expressionAssignment returns [Expression lvalue, expression rvalue, Expression expr]:
		 expr_lvalue = expressionOr '=' expr_rvalue = expressionAssignment {
             $lvalue = $expr_lvalue.expr; $rvalue = $expr_rvalue.expr; 
             BinaryOperator bo = BinaryOperator.assign;
             BineryExpression be = new BinaryExpression($expr_lvalue.expr, $expr_rvalue.expr, bo);
             $expr = be;
         }
         | exp = expressionOr {$expr = exp.expr; $rvalue = Null; $lvalue = Null;}
	;

    expressionOr returns [Expression expr, BinaryOperator be]:
		lvalue = expressionAnd rvalue = expressionOrTemp{
            if($rvalue.expr != Null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
            }
            else{
                expr = $lvalue.expr;
            }
        }
	;

    expressionOrTemp returns [Expression expr, BinaryOperator bo]:
		'||'{$bo = BinaryOperator.or;} expr1 = expressionAnd epxr2 = expressionOrTemp
        {$expr= new BinaryExpression($expr1.expr, $expr2.expr, $expr2.be);}
	    |
	;

    expressionAnd returns [Expression expr]:
		lvalue = expressionEq rvalue = expressionAndTemp
        {
            if($rvalue.expr != Null){
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
            if($rvalue.expr != Null){
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
            if($rvalue.expr != Null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
        }
            else{
                $expr = $lvalue.expr;
            }
        }
	;

    expressionCmpTemp returns [Expression expr, BinaryOperator bo]:
		('<' {$bo = BinaryOperator.lt;} | '>' {$bo = BinaryOperator.bt;}) 
        expr1 = expressionAdd expr2 = expressionCmpTemp 
        {$expr = new BinaryExpression($expr1.expr, $expr2.expr, $expr2.bo);}
	    |
	;

    expressionAdd returns [Expression expr]:
		lvalue = expressionMult rvalue = expressionAddTemp{
            if($rvalue.expr != Null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
        }
            else{
                $expr = $lvalue.expr;
            }
        }
	;

    expressionAddTemp returns [Expression expr, BinaryOperator bo]:
		('+'{$bo = BinaryOperator.add;} | '-'{$bo = BinaryOperator.sub;}) 
        expr1 = expressionMult expr2 =  expressionAddTemp
        {$bo = new BinaryOperator($expr1.expr, $expr2.expr, $expr2.bo);}
	    |
	;

        expressionMult returns [Expression expr]:
		lvalue = expressionUnary rvalue = expressionMultTemp{
            if($rvalue.expr != Null){
                $expr = new BinaryExpression($lvalue.expr, $rvalue.expr, $rvalue.bo);
        }
            else{
                $expr = $lvalue.expr;
            }
        }
	;

    expressionMultTemp returns [Expression expr, BinaryOperator bo]:
		('*' {$bo = BinaryOperator.mult;}| '/'{$bo = BinaryOperator.div;}) 
        expr1 = expressionUnary expr2 = expressionMultTemp
        {$expr = new BinaryExpression($expr1.expr, $expr2.expr, $expr2.bo;);}
	    |
	;

    expressionUnary returns [Expression expr]:
		('!' {UnaryOperator uo = UnaryOperator.not;} | '-' {UnaryOperator uo = UnaryOperator.sub;}) exp = expressionUnary
        {$expr = new UnaryExpression(uo, $exp.expr);}
	    |	exp = expressionMem {$expr = $exp.expr;}
	;

    expressionMem returns [Expression expr]:
		instance = expressionMethods index = expressionMemTemp
        {$expr = new ArrayCall($instance.expr, $index.expr);}
	;

    expressionMemTemp returns [Expression expr]:
		'[' index = expression ']' {$expr = $index;}
	    |
	;
	expressionMethods returns [Expression expr]: // not sure
	    instance = expressionOther methodcall = expressionMethodsTemp[instance] {$expr = $methodcall.expr;}
	;
	expressionMethodsTemp[Expression instance] returns [MethodCall methodcall]:
	    '.'  ((methodname = ID '(' ')'{   
                Identifier id = new Identifier($methodname.text);
                mc = new MethodCall(instance, id);
            }) 
        | methodname = ID '(' {
                Identifier id = new Identifier($methodname.text);
                mc = new MethodCall(instance, id);
            }
        (arg = expression {mc.addArg(arg.expr);} (',' arg = expression {mc.addArg(arg.expr);})*) ')' 
        | 'length' {Length len = new Length(instance); }) 
        expressionMethodsTemp[this_instance]
	    |
	; // incomplete

    expressionOther returns [Expression expr، Expression lvalue , Expression rvalue]:
		num = CONST_NUM
        {   
                IntType t = new IntType();
                IntValue intval = new IntValue(t, $num.int);
                $expr  = intval;
            }
        |	str = CONST_STR {
            StringType st = new StringType();
            StringValue strval = new StringValue(st, $str.text);
            $expr = strval;
        }
            
        | 'new ' 'int' '[' num = CONST_NUM ']'
            {   
                $expr = new NewArray();
                IntValue val = new IntValue($num.int);
                $expr.setExpression(val);
            }
        |   'new ' name = ID '(' ')' {
            Identifier id = new Identifier($name.text);
            $expr = new NewClass(id);
            }
        |   'this' { $expr = new This();}
        |   constval = 'true' {
                                BooleanType bt = new BooleanType(); 
                                $expr = new BooleanValue($constval.text, bt); 
                             }
        |   constval = 'false'{ BooleanType bt = new BooleanType();
                                $expr = new BooleanValue($constval.text, bt);
                                }
        |	id = ID {$expr = new Identifier($id.text);}
        |   id = ID '[' exp = expression ']' 
            {     
                Identifier identifier = new Identifier($id.text);
                $expr = new ArrayCall(identifier, $exp.expr);
            }
        |	'(' thisexpr = expression ')' {
            if($expr = Null){ // this is a binary expression so we return lvalue and rvalue 
                $lvalue = thisexpr.lvaue;
                $rvalue = thisexpr.rvalue;
                $
            }
            else $expr = thisexpr.expr;
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