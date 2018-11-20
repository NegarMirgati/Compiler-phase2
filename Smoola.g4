grammar Smoola;
@header { 
    import ast.Type.*;
    import ast.node.Program;
    import ast.node.Declaration.*;
    import ast.node.expression.*;
    import ast.node.type.*;

}

@member {

}

    program:
        {Program prog = new Program();} mainClass[prog] (classDeclaration)* EOF 
    ;
    mainClass[Program Prog] returns [ClassDeclaration classDec]:
        'class' className = ID {
            Identifier id = new Identifier(className);
            $classDec = new classDeclaration(className, Null);
            MainMethodDeclaration mainMethodDec = new MainMethodDeclaration();
        }
        '{' 'def' methodname = ID '(' ')' ':' 'int' '{'  stms = statements{
            for (int i = 0; i < stms.size(); i++) {
                mainMethodDec.addStatement(stms.get(i));
		    }
         }
         'return' retexp = expression  
         {  mainMethodDec.setReturnValue($retexp.expr);
            mainClass.addMethodDeclaration(mainMethod);
            prog.setMainClass(mainClass);}';' '}' '}'
         ;

    classDeclaration returns [ClassDeclaration classDec]:
        'class' classname = ID ('extends' parentname = ID)?
        {
            Identifier classid = new Identifier(classname);
            Identifier parentclassid = new Identifier(parentname);
            classDecObj = new ClassDeclaration(classid, parentclassid);
        }
        '{' (vardec = varDeclaration {classDecObj.addVarDeclaration($vardec.varDec);})* 
            (methoddec = methodDeclaration {classDecObj.addMethodDeclaration($methoddec.methodDec);})* '}'
        {$classDec = classDecObj;}    // not sure
    ;
    varDeclaration returns [VarDeclaration varDec]:
        'var' name = ID ':' t = type ';' 
        {
            Identifier id = new Identifier(name);
            $varDec = new VarDeclaration(id, $t.t);

        }
    ;
    methodDeclaration returns[MethodDeclaration methodDec]:
        'def' methodname = ID{
            Identifier id = new Identifier(methodname);
            methodDeclaration = new MethodDeclaration(id);
        }
        ('(' ')' | ('(' id = ID ':' tp = type {   
            Identifier vardecid = new Identifier(id);
            VarDeclaration arg = new VarDeclaration(vardecid, $tp.t);
            methodDeclaration.addArg(arg);}
        (',' id = ID ':' tp = type
        {
            Identifier vardecid = new Identifier(id);
            VarDeclaration arg = new VarDeclaration(vardecid, $tp.t);
            methodDeclaration.addArg(arg);
        })* ')')) ':' 
        rettype = type '{' { methodDeclaration.setReturnValue($rettype.t); }
        (vardec = varDeclaration { methodDeclaration.addLocalVar($vardec.varDec); })*
        stms = statements {
            for(int i = 0; i < stms.size(); i++){
                methodDeclaration.addStatement(stms.get(i));
            }
        }
        'return' retvalexpr = expression {methodDeclaration.setReturnValue($retvalexpr.expr);}';' '}'
        {$methodDec = methodDeclaration;} /// not sure
 
    ;
    statements returns [ArrayList<Statement> multipleStatements]:
        {ArrayList<Statement> multipleStatements = new ArrayList<>();}
        (stm = statement{multipleStatements.add($stm.stm);})*
    ;
    statement returns[Statement stm]:
        st = statementBlock {$stm = st.multipleStatements;} |
        st = statementCondition {$stm = st.conditional;} |
        st = statementLoop {$stm = st.while;} |
        st = statementWrite {$stm = st.stm_write;} |
        st = statementAssignment   {$stm = st.stm;}
      
    ;
    statementBlock returns [ArrayList<Statement> multipleStatements]:
        '{'  stms = statements {$multipleStatements = $stms.multipleStatements;} '}' // not sure about shallow copying
    ;
    statementCondition returns [Conditional conditional]: // incomplete
        'if' '('expr = expression')' {}'then' cst = statement ('else' ast = statement {})?
      
    ;
    statementLoop returns [While while]:
        'while' '(' expr = expression ')' st = statement {
            Expression condition, Statement body
            $while = new While($expr.expr, $st.stm);
        }
    ;
    statementWrite returns [Write stm_write]:
        'writeln(' expr = expression ')' ';' {$stm_write = new Write($expr.expr);} 
    ;
    statementAssignment returns [Assign assign]:
        expr = expression ';'
    ;

    expression returns [Expression expr]:
		exp = expressionAssignment {}
	;

    expressionAssignment:
		expressionOr '='
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
		expressionMethods expressionMemTemp
	;

    expressionMemTemp:
		'[' expression ']'
	    |
	;
	expressionMethods:
	    expressionOther expressionMethodsTemp
	;
	expressionMethodsTemp:
	    '.' (ID '(' ')' | ID '(' (expression (',' expression)*) ')' | 'length') expressionMethodsTemp
	    |
	;
    expressionOther returns [Expression expr, Expression rvalue, Expression lvalue]:
		num = CONST_NUM
        {   
                IntType t = new IntType();
                IntValue intval = new IntValue(t, $num.int);
                $expr  = intval;
                $rvalue = Null;
                $lvalue = Null;
            }
        |	str = CONST_STR {
            StringType st = new StringType();
            StringValue strval = new StringValue(st, $str.); // str.?
            $expr = strva;
            $rvalue = Null;
            $lvalue = Null;
        }
            
        |   'new ' 'int' '[' exp = expression ']'
            {   
                $expr = new NewArray();
                $expr.setExpression($exp.expr);
            }
        |   'new ' name = ID '(' ')' {
            Identifier id = new Identifier(name);
            $expr = new NewClass(id);
            $rvalue = Null;
            $lvalue = Null;
            }
        |   'this' { $expr = new This();}
        |   constval = 'true' {
                                BooleanType bt = new BooleanType(); 
                                $expr = new BooleanValue(constval, bt); 
                                $rvalue = Null;
                                $lvalue = Null;
                             }
        |   constval = 'false'{BooleanType bt = new BooleanType(); $expr = new BooleanValue(constval, bt);}
        |	id = ID {$expr = new Identifier(id);}
        |   id = ID '[' exp = expression ']' 
            {     
                Identifier identifier = new Identifier(id);
                $expr = new ArrayCall(identifier, $exp.expr);
                $rvalue = Null;
                $lvalue = Null;
            }
        |	'(' thisexpr = expression ')' {
            if($expr = Null){ // this is a binary expression so we return lvalue and rvalue 
                $lvalue = thisexpr.lvaue;
                $rvalue = thisexpr.rvalue;
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