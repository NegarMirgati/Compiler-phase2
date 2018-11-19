grammar Smoola;
@header { 
    import ast.Type.PrimitiveType.IntType;
    import ast.node.Program;
    import ast.node.Declaration.*;
    import ast.node.expression.Value.IntValue;
    import ast.node.type.*;

}

@member {

}

    program:
        mainClass (classDeclaration)* EOF {Program prog = new Program();}
    ;
    mainClass[Program Prog] returns [ClassDeclaration classDec]:
        'class' className = ID {
            Identifier id = new Identifier(className);
            $classDec = new classDeclaration(className, Null);
            MainMethodDeclaration mainMethodDec = new MainMethodDeclaration();
            prog.setMainClass(mainMethodDec);
        }
        '{' 'def' methodname = ID '(' ')' ':' 'int' '{'  statements 'return' expression ';' '}' '}';

    classDeclaration returns [ClassDeclaration classDec]:
        'class' classname = ID ('extends' parentname = ID)?
        {
            Identifier classid = new Identifier(classname);
            Identifier parentclassid = new Identifier(parentname);
            $classDec = new ClassDeclaration(classid, parentclassid);
        }
        '{' (varDeclaration)* (methodDeclaration)* '}'
    ;
    varDeclaration returns [VarDeclaration varDeclaration]:
        'var' name = ID ':' t = type ';' 
        {
            Identifier id = new Identifier(name);
            $varDeclaration = new VarDeclaration(id, $t)

        }
    ;
    methodDeclaration returns[MethodDeclaration methodDeclaration]:
        'def' methodname = ID{
            Identifier id = new Identifier(methodname);
            methodDeclaration = new MethodDeclaration(id);
        }
        ('(' ')' | ('(' ID ':' type (',' ID ':' type)* ')')) ':' type '{'  varDeclaration* statements 'return' expression ';' '}'
 
    ;
    statements returns [ArrayList<Statement> multipleStatements]:
        {ArrayList<Statement> multipleStatements = new ArrayList<>();}
        (stm = statement{multipleStatements.add($stm.stm);})*
    ;
    statement returns[Statement stm]:
        statementBlock |
        statementCondition |
        statementLoop |
        statementWrite |
        statementAssignment
    ;
    statementBlock returns [ArrayList<Statement> multipleStatements]:
        '{'  statements '}'
    ;
    statementCondition returns [Conditional conditional]:
        'if' '('expr = expression')' 'then' cst = statement ('else' statement)?
        {
            $conditional = new Conditional($expr.expr, $cst.stm);
        }
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
    statementAssignment:
        expression ';'
    ;

    expression returns [Expression expr]:
		expressionAssignment
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
    expressionOther returns [Expression expr]:
		CONST_NUM
        |	CONST_STR
        |   'new ' 'int' '[' exp = expression ']'{$expr = new NewArray();
                                                  $expr.setExpression($exp.expr);
                                                 }
        |   'new ' name = ID '(' ')' {
            Identifier id = new Identifier(name);
            $expr = new NewClass(id);}
        |   'this' { $expr = new This();}
        |   const = 'true' {BooleanType bt = new BooleanType(); $expr = new BooleanValue(const, bt);}
        |   const = 'false'{BooleanType bt = new BooleanType(); $expr = new BooleanValue(const, bt);}
        |	id = ID {$expr = new Identifier(id);}
        |   id = ID '[' exp = expression ']' 
            {     
                Identifier identifier = new Identifier(id);
                $expr = new ArrayCall(identifier, $exp.expr);
            }
        |	'(' thisexpr = expression ')' {$expr = thisexpr.expr;} 
	;
	type returns [Type t]:
	    'int' {$type = new IntType();}|
	    'boolean' {$type = new BooleanType();}|
	    'string' {$type = new StringType();}|
	    'int' '[' ']' {$type = new ArrayType();}|
	    ID {$type = new UserDefinedType();}
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