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
        {ArrayList<Statement> multipleStatements = new ArrayList<>();}
        (stm = statement{ $multipleStatements.add($stm.stm); })*
    ;
    statement returns[Statement stm]:
        st = statementBlock {$stm = $st.multipleStatements;} |
        st = statementCondition {$stm = $st.conditional;} |
        st = statementLoop {$stm = $st.while;} |
        st = statementWrite {$stm = $st.stm_write;} |
        st = statementAssignment   {$stm = $st.stm;}
      
    ;
    statementBlock returns [ArrayList<Statement> multipleStatements]:
        '{' stms = statements {$multipleStatements = $stms.multipleStatements;} '}' // not sure about shallow copying
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

    expression returns [Expression expr]:
		exp = expressionAssignment {}
	;

    expressionAssignment:
		expr_lvalue = expressionOr '=' {}
	    |	expressionOr
	;

    expressionOr:
		expressionAnd expressionOrTemp
	;

    expressionOrTemp:
		'||' expressionAnd expressionOrTemp
	    |
	;

    expressionAnd:
		expressionEq expressionAndTemp
	;

    expressionAndTemp:
		'&&' expressionEq expressionAndTemp
	    |
	;

    expressionEq:
		expressionCmp expressionEqTemp
	;

    expressionEqTemp:
		('==' | '<>') expressionCmp expressionEqTemp
	    |
	;

    expressionCmp:
		expressionAdd expressionCmpTemp
	;

    expressionCmpTemp:
		('<' | '>') expressionAdd expressionCmpTemp
	    |
	;

    expressionAdd:
		expressionMult expressionAddTemp
	;

    expressionAddTemp:
		('+' | '-') expressionMult expressionAddTemp
	    |
	;

        expressionMult:
		expressionUnary expressionMultTemp
	;

    expressionMultTemp:
		('*' | '/') expressionUnary expressionMultTemp
	    |
	;

    expressionUnary:
		('!' | '-') expressionUnary
	    |	expressionMem
	;

    expressionMem:
		instance = expressionMethods expr_mem = expressionMemTemp
	;

    expressionMemTemp returns [Expression expr]:
		'[' exp = expression ']' { $expr = exp.expr; }
	    |
	;
	expressionMethods:
	    instance = expressionOther expressionMethodsTemp[$instance]
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

    expressionOther returns [Expression expr]:
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