// Generated from Smoola.g4 by ANTLR 4.7.1
 
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
    import symbolTable.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SmoolaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, CONST_NUM=41, CONST_STR=42, NL=43, ID=44, COMMENT=45, 
		WS=46;
	public static final int
		RULE_program = 0, RULE_mainClass = 1, RULE_classDeclaration = 2, RULE_varDeclaration = 3, 
		RULE_methodDeclaration = 4, RULE_statements = 5, RULE_statement = 6, RULE_statementBlock = 7, 
		RULE_statementCondition = 8, RULE_statementLoop = 9, RULE_statementWrite = 10, 
		RULE_statementAssignment = 11, RULE_expression = 12, RULE_expressionAssignment = 13, 
		RULE_expressionOr = 14, RULE_expressionOrTemp = 15, RULE_expressionAnd = 16, 
		RULE_expressionAndTemp = 17, RULE_expressionEq = 18, RULE_expressionEqTemp = 19, 
		RULE_expressionCmp = 20, RULE_expressionCmpTemp = 21, RULE_expressionAdd = 22, 
		RULE_expressionAddTemp = 23, RULE_expressionMult = 24, RULE_expressionMultTemp = 25, 
		RULE_expressionUnary = 26, RULE_expressionMem = 27, RULE_expressionMemTemp = 28, 
		RULE_expressionMethods = 29, RULE_expressionMethodsTemp = 30, RULE_expressionOther = 31, 
		RULE_type = 32;
	public static final String[] ruleNames = {
		"program", "mainClass", "classDeclaration", "varDeclaration", "methodDeclaration", 
		"statements", "statement", "statementBlock", "statementCondition", "statementLoop", 
		"statementWrite", "statementAssignment", "expression", "expressionAssignment", 
		"expressionOr", "expressionOrTemp", "expressionAnd", "expressionAndTemp", 
		"expressionEq", "expressionEqTemp", "expressionCmp", "expressionCmpTemp", 
		"expressionAdd", "expressionAddTemp", "expressionMult", "expressionMultTemp", 
		"expressionUnary", "expressionMem", "expressionMemTemp", "expressionMethods", 
		"expressionMethodsTemp", "expressionOther", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'class'", "'{'", "'def'", "'('", "')'", "':'", "'int'", "'return'", 
		"';'", "'}'", "'extends'", "'var'", "','", "'if'", "'then'", "'else'", 
		"'while'", "'writeln('", "'='", "'||'", "'&&'", "'=='", "'<>'", "'<'", 
		"'>'", "'+'", "'-'", "'*'", "'/'", "'!'", "'['", "']'", "'.'", "'length'", 
		"'new '", "'this'", "'true'", "'false'", "'boolean'", "'string'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "CONST_NUM", "CONST_STR", "NL", "ID", "COMMENT", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Smoola.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    boolean noClasses = true;
	    ArrayList<UserDefinedType> incompleteTypes = new ArrayList <> ();

	    void print(String str){
	        System.out.println(str);
	    }
	    void setIncompleteTypes(Program prog){

	        for(int i = 0; i < incompleteTypes.size(); i++){
	            UserDefinedType t = incompleteTypes.get(i);
	            Identifier className = t.getName();
	            ArrayList<ClassDeclaration> classes = new ArrayList<>(prog.getClasses());
	            ClassDeclaration cd = findCorrespondingClassDec(prog,className);
	            incompleteTypes.get(i).setClassDeclaration(cd);
	        }
	    }

	    ClassDeclaration findCorrespondingClassDec(Program prog, Identifier id){
	        ArrayList<ClassDeclaration> allClasses = new ArrayList<>(prog.getClasses());
	        allClasses.add(prog.getMainClass());
	        for(int i = 0; i < allClasses.size(); i++){
	            String s = "Identifier ";
	            if(allClasses.get(i).getName().getName().equals(id.getName())){
	                return (allClasses.get(i));
	            }
	        }
	        return null;
	    }
	    

	public SmoolaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public Program prog;
		public MainClassContext mainClass() {
			return getRuleContext(MainClassContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SmoolaParser.EOF, 0); }
		public List<ClassDeclarationContext> classDeclaration() {
			return getRuleContexts(ClassDeclarationContext.class);
		}
		public ClassDeclarationContext classDeclaration(int i) {
			return getRuleContext(ClassDeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			Program prog = new Program();
			setState(67);
			mainClass(prog);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(68);
				classDeclaration(prog);
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(74);
			match(EOF);

			            if(noClasses == true)
			                print("Line:0:No class exists in the program"); 
			            else{
			                setIncompleteTypes(prog);
				            VisitorImpl v = new VisitorImpl();
			                prog.accept(v);
			            }

			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MainClassContext extends ParserRuleContext {
		public Program prog;
		public ClassDeclaration main;
		public Token cl;
		public Token className;
		public Token ml;
		public Token methodname;
		public StatementsContext stms;
		public ExpressionContext retexp;
		public List<TerminalNode> ID() { return getTokens(SmoolaParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SmoolaParser.ID, i);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MainClassContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public MainClassContext(ParserRuleContext parent, int invokingState, Program prog) {
			super(parent, invokingState);
			this.prog = prog;
		}
		@Override public int getRuleIndex() { return RULE_mainClass; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterMainClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitMainClass(this);
		}
	}

	public final MainClassContext mainClass(Program prog) throws RecognitionException {
		MainClassContext _localctx = new MainClassContext(_ctx, getState(), prog);
		enterRule(_localctx, 2, RULE_mainClass);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			((MainClassContext)_localctx).cl = match(T__0);
			noClasses = false;
			int line = ((MainClassContext)_localctx).cl.getLine();
			setState(80);
			((MainClassContext)_localctx).className = match(ID);

			            Identifier id = new Identifier((((MainClassContext)_localctx).className!=null?((MainClassContext)_localctx).className.getText():null));
			            ((MainClassContext)_localctx).main =  new ClassDeclaration(id, null);
			            _localctx.main.setLine(line);
			        
			setState(82);
			match(T__1);
			setState(83);
			((MainClassContext)_localctx).ml = match(T__2);
			int mline = ((MainClassContext)_localctx).ml.getLine();
			setState(85);
			((MainClassContext)_localctx).methodname = match(ID);
			setState(86);
			match(T__3);
			setState(87);
			match(T__4);
			setState(88);
			match(T__5);
			setState(89);
			match(T__6);

			            Identifier mid = new Identifier((((MainClassContext)_localctx).methodname!=null?((MainClassContext)_localctx).methodname.getText():null));
			            MethodDeclaration mainMethodDec = new MethodDeclaration(mid);
			            mainMethodDec.setLine(mline);
			            IntType t = new IntType(); mainMethodDec.setReturnType(t);

			            
			setState(91);
			match(T__1);
			setState(92);
			((MainClassContext)_localctx).stms = statements();

			            for (int i = 0; i < (((MainClassContext)_localctx).stms.multipleStatements).size(); i++) {
			                mainMethodDec.addStatement(((MainClassContext)_localctx).stms.multipleStatements.get(i));
					    }
			         
			setState(94);
			match(T__7);
			setState(95);
			((MainClassContext)_localctx).retexp = expression();
			  
			            mainMethodDec.setReturnValue(((MainClassContext)_localctx).retexp.expr);
			            _localctx.main.addMethodDeclaration(mainMethodDec);
			            _localctx.prog.setMainClass(_localctx.main);
			            
			setState(97);
			match(T__8);
			setState(98);
			match(T__9);
			setState(99);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public Program prog;
		public ClassDeclaration classDec;
		public Token cl;
		public Token classname;
		public Token parentname;
		public VarDeclarationContext vardec;
		public MethodDeclarationContext methoddec;
		public List<TerminalNode> ID() { return getTokens(SmoolaParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SmoolaParser.ID, i);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public List<MethodDeclarationContext> methodDeclaration() {
			return getRuleContexts(MethodDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration(int i) {
			return getRuleContext(MethodDeclarationContext.class,i);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState, Program prog) {
			super(parent, invokingState);
			this.prog = prog;
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration(Program prog) throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState(), prog);
		enterRule(_localctx, 4, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			noClasses = false;
			setState(102);
			((ClassDeclarationContext)_localctx).cl = match(T__0);
			int line = ((ClassDeclarationContext)_localctx).cl.getLine();
			setState(104);
			((ClassDeclarationContext)_localctx).classname = match(ID);
			setState(107);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(105);
				match(T__10);
				setState(106);
				((ClassDeclarationContext)_localctx).parentname = match(ID);
				}
			}


			            Identifier classid = new Identifier((((ClassDeclarationContext)_localctx).classname!=null?((ClassDeclarationContext)_localctx).classname.getText():null));
			            Identifier parentclassid;
			            if((((ClassDeclarationContext)_localctx).parentname!=null?((ClassDeclarationContext)_localctx).parentname.getText():null) != null)
			                parentclassid = new Identifier((((ClassDeclarationContext)_localctx).parentname!=null?((ClassDeclarationContext)_localctx).parentname.getText():null));
			            else    
			                parentclassid = null;

			            ((ClassDeclarationContext)_localctx).classDec =  new ClassDeclaration(classid, parentclassid);
			            _localctx.classDec.setLine(line);
			        
			setState(110);
			match(T__1);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(111);
				((ClassDeclarationContext)_localctx).vardec = varDeclaration();
				_localctx.classDec.addVarDeclaration(((ClassDeclarationContext)_localctx).vardec.varDec);
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(119);
				((ClassDeclarationContext)_localctx).methoddec = methodDeclaration();
				_localctx.classDec.addMethodDeclaration(((ClassDeclarationContext)_localctx).methoddec.methodDec);
				}
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(127);
			match(T__9);
			   
			            _localctx.prog.addClass(_localctx.classDec);
			            
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public VarDeclaration varDec;
		public Token vl;
		public Token name;
		public TypeContext t;
		public TerminalNode ID() { return getToken(SmoolaParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitVarDeclaration(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_varDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			((VarDeclarationContext)_localctx).vl = match(T__11);
			int line = ((VarDeclarationContext)_localctx).vl.getLine(); 
			setState(132);
			((VarDeclarationContext)_localctx).name = match(ID);
			setState(133);
			match(T__5);
			setState(134);
			((VarDeclarationContext)_localctx).t = type();
			setState(135);
			match(T__8);

			            Identifier id = new Identifier((((VarDeclarationContext)_localctx).name!=null?((VarDeclarationContext)_localctx).name.getText():null));
			            ((VarDeclarationContext)_localctx).varDec =  new VarDeclaration(id, ((VarDeclarationContext)_localctx).t.t);
			            _localctx.varDec.setLine(line);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public MethodDeclaration methodDec;
		public Token ml;
		public Token methodname;
		public Token vl;
		public Token id;
		public TypeContext tp;
		public Token vl2;
		public TypeContext rettype;
		public VarDeclarationContext vardec;
		public StatementsContext stms;
		public ExpressionContext retvalexpr;
		public List<TerminalNode> ID() { return getTokens(SmoolaParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(SmoolaParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitMethodDeclaration(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_methodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			((MethodDeclarationContext)_localctx).ml = match(T__2);
			int line = ((MethodDeclarationContext)_localctx).ml.getLine();
			setState(140);
			((MethodDeclarationContext)_localctx).methodname = match(ID);

			            Identifier id = new Identifier((((MethodDeclarationContext)_localctx).methodname!=null?((MethodDeclarationContext)_localctx).methodname.getText():null));
			            ((MethodDeclarationContext)_localctx).methodDec =  new MethodDeclaration(id);
			            _localctx.methodDec.setLine(line);
			        
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(142);
				match(T__3);
				setState(143);
				match(T__4);
				}
				break;
			case 2:
				{
				{
				setState(144);
				((MethodDeclarationContext)_localctx).vl = match(T__3);
				int vline = ((MethodDeclarationContext)_localctx).vl.getLine();
				setState(146);
				((MethodDeclarationContext)_localctx).id = match(ID);
				setState(147);
				match(T__5);
				setState(148);
				((MethodDeclarationContext)_localctx).tp = type();
				   
				            Identifier vardecid = new Identifier((((MethodDeclarationContext)_localctx).id!=null?((MethodDeclarationContext)_localctx).id.getText():null));
				            VarDeclaration arg = new VarDeclaration(vardecid, ((MethodDeclarationContext)_localctx).tp.t);
				            arg.setLine(vline);
				            _localctx.methodDec.addArg(arg);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(150);
					((MethodDeclarationContext)_localctx).vl2 = match(T__12);
					 int vline2 = ((MethodDeclarationContext)_localctx).vl2.getLine(); 
					setState(152);
					((MethodDeclarationContext)_localctx).id = match(ID);
					setState(153);
					match(T__5);
					setState(154);
					((MethodDeclarationContext)_localctx).tp = type();

					            Identifier vardecid2 = new Identifier((((MethodDeclarationContext)_localctx).id!=null?((MethodDeclarationContext)_localctx).id.getText():null));
					            VarDeclaration arg2 = new VarDeclaration(vardecid2, ((MethodDeclarationContext)_localctx).tp.t);
					            arg2.setLine(vline2);
					            _localctx.methodDec.addArg(arg2);
					        
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(162);
				match(T__4);
				}
				}
				break;
			}
			setState(166);
			match(T__5);
			setState(167);
			((MethodDeclarationContext)_localctx).rettype = type();
			setState(168);
			match(T__1);
			 _localctx.methodDec.setReturnType(((MethodDeclarationContext)_localctx).rettype.t); 
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(170);
				((MethodDeclarationContext)_localctx).vardec = varDeclaration();
				 _localctx.methodDec.addLocalVar(((MethodDeclarationContext)_localctx).vardec.varDec); 
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(178);
			((MethodDeclarationContext)_localctx).stms = statements();

			            for(int i = 0; i < ((MethodDeclarationContext)_localctx).stms.multipleStatements.size(); i++){
			                _localctx.methodDec.addStatement(((MethodDeclarationContext)_localctx).stms.multipleStatements.get(i));
			            }
			        
			setState(180);
			match(T__7);
			setState(181);
			((MethodDeclarationContext)_localctx).retvalexpr = expression();
			_localctx.methodDec.setReturnValue(((MethodDeclarationContext)_localctx).retvalexpr.expr);
			setState(183);
			match(T__8);
			setState(184);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementsContext extends ParserRuleContext {
		public ArrayList<Statement> multipleStatements;
		public StatementContext stm;
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitStatements(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((StatementsContext)_localctx).multipleStatements =  new ArrayList<>();
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__13) | (1L << T__16) | (1L << T__17) | (1L << T__26) | (1L << T__29) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << CONST_NUM) | (1L << CONST_STR) | (1L << ID))) != 0)) {
				{
				{
				setState(187);
				((StatementsContext)_localctx).stm = statement();
				_localctx.multipleStatements.add(((StatementsContext)_localctx).stm.stm);
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Statement stm;
		public StatementBlockContext st;
		public StatementConditionContext st1;
		public StatementLoopContext st2;
		public StatementWriteContext st3;
		public StatementAssignmentContext st4;
		public StatementBlockContext statementBlock() {
			return getRuleContext(StatementBlockContext.class,0);
		}
		public StatementConditionContext statementCondition() {
			return getRuleContext(StatementConditionContext.class,0);
		}
		public StatementLoopContext statementLoop() {
			return getRuleContext(StatementLoopContext.class,0);
		}
		public StatementWriteContext statementWrite() {
			return getRuleContext(StatementWriteContext.class,0);
		}
		public StatementAssignmentContext statementAssignment() {
			return getRuleContext(StatementAssignmentContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		try {
			setState(210);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				((StatementContext)_localctx).st = statementBlock();
				((StatementContext)_localctx).stm =  ((StatementContext)_localctx).st.block;
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				((StatementContext)_localctx).st1 = statementCondition();
				((StatementContext)_localctx).stm =  ((StatementContext)_localctx).st1.conditional;
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 3);
				{
				setState(201);
				((StatementContext)_localctx).st2 = statementLoop();
				((StatementContext)_localctx).stm =  ((StatementContext)_localctx).st2.wh;
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 4);
				{
				setState(204);
				((StatementContext)_localctx).st3 = statementWrite();
				((StatementContext)_localctx).stm =  ((StatementContext)_localctx).st3.stm_write;
				}
				break;
			case T__3:
			case T__26:
			case T__29:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case CONST_NUM:
			case CONST_STR:
			case ID:
				enterOuterAlt(_localctx, 5);
				{
				setState(207);
				((StatementContext)_localctx).st4 = statementAssignment();
				((StatementContext)_localctx).stm =  (((StatementContext)_localctx).st4.assign);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementBlockContext extends ParserRuleContext {
		public Block block;
		public StatementsContext stms;
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public StatementBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterStatementBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitStatementBlock(this);
		}
	}

	public final StatementBlockContext statementBlock() throws RecognitionException {
		StatementBlockContext _localctx = new StatementBlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_statementBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			match(T__1);
			((StatementBlockContext)_localctx).block =  new Block();
			setState(214);
			((StatementBlockContext)_localctx).stms = statements();

			            for(int i = 0 ; i < ((StatementBlockContext)_localctx).stms.multipleStatements.size(); i ++ ){
			                _localctx.block.addStatement(((StatementBlockContext)_localctx).stms.multipleStatements.get(i));
			            }
			        
			setState(216);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementConditionContext extends ParserRuleContext {
		public Conditional conditional;
		public ExpressionContext exp;
		public StatementContext cst;
		public StatementContext ast;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterStatementCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitStatementCondition(this);
		}
	}

	public final StatementConditionContext statementCondition() throws RecognitionException {
		StatementConditionContext _localctx = new StatementConditionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statementCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(218);
			match(T__13);
			setState(219);
			match(T__3);
			setState(220);
			((StatementConditionContext)_localctx).exp = expression();
			setState(221);
			match(T__4);
			setState(222);
			match(T__14);
			setState(223);
			((StatementConditionContext)_localctx).cst = statement();
			Conditional cond = new Conditional(((StatementConditionContext)_localctx).exp.expr, ((StatementConditionContext)_localctx).cst.stm);
			setState(229);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(225);
				match(T__15);
				setState(226);
				((StatementConditionContext)_localctx).ast = statement();
				cond.setAlternativeBody(((StatementConditionContext)_localctx).ast.stm);
				}
				break;
			}
			((StatementConditionContext)_localctx).conditional =  cond;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementLoopContext extends ParserRuleContext {
		public While wh;
		public ExpressionContext exp;
		public StatementContext st;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterStatementLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitStatementLoop(this);
		}
	}

	public final StatementLoopContext statementLoop() throws RecognitionException {
		StatementLoopContext _localctx = new StatementLoopContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_statementLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(T__16);
			setState(234);
			match(T__3);
			setState(235);
			((StatementLoopContext)_localctx).exp = expression();
			setState(236);
			match(T__4);
			setState(237);
			((StatementLoopContext)_localctx).st = statement();

			            ((StatementLoopContext)_localctx).wh =  new While(((StatementLoopContext)_localctx).exp.expr, ((StatementLoopContext)_localctx).st.stm);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementWriteContext extends ParserRuleContext {
		public Write stm_write;
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementWriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementWrite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterStatementWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitStatementWrite(this);
		}
	}

	public final StatementWriteContext statementWrite() throws RecognitionException {
		StatementWriteContext _localctx = new StatementWriteContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_statementWrite);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(T__17);
			setState(241);
			((StatementWriteContext)_localctx).expr = expression();
			setState(242);
			match(T__4);
			setState(243);
			match(T__8);
			((StatementWriteContext)_localctx).stm_write =  new Write(((StatementWriteContext)_localctx).expr.expr);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementAssignmentContext extends ParserRuleContext {
		public Assign assign;
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterStatementAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitStatementAssignment(this);
		}
	}

	public final StatementAssignmentContext statementAssignment() throws RecognitionException {
		StatementAssignmentContext _localctx = new StatementAssignmentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statementAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			((StatementAssignmentContext)_localctx).expr = expression();
			setState(247);
			match(T__8);

			            if(((StatementAssignmentContext)_localctx).expr.lvalue != null && ((StatementAssignmentContext)_localctx).expr.rvalue != null){

			                ((StatementAssignmentContext)_localctx).assign =  new Assign(((StatementAssignmentContext)_localctx).expr.lvalue, ((StatementAssignmentContext)_localctx).expr.rvalue);
			            }
			            else if(((StatementAssignmentContext)_localctx).expr.expr != null)
			                ((StatementAssignmentContext)_localctx).assign =  new Assign(((StatementAssignmentContext)_localctx).expr.expr, null);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression lvalue;
		public Expression rvalue;
		public Expression expr;
		public ExpressionAssignmentContext retval;
		public ExpressionAssignmentContext expressionAssignment() {
			return getRuleContext(ExpressionAssignmentContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			((ExpressionContext)_localctx).retval = expressionAssignment();

			            ((ExpressionContext)_localctx).rvalue =  ((ExpressionContext)_localctx).retval.rvalue;
			            ((ExpressionContext)_localctx).lvalue =  ((ExpressionContext)_localctx).retval.lvalue;
			            ((ExpressionContext)_localctx).expr =  ((ExpressionContext)_localctx).retval.expr;
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionAssignmentContext extends ParserRuleContext {
		public Expression lvalue;
		public Expression rvalue;
		public Expression expr;
		public ExpressionOrContext expr_lvalue;
		public ExpressionAssignmentContext expr_rvalue;
		public ExpressionOrContext exp;
		public ExpressionOrContext expressionOr() {
			return getRuleContext(ExpressionOrContext.class,0);
		}
		public ExpressionAssignmentContext expressionAssignment() {
			return getRuleContext(ExpressionAssignmentContext.class,0);
		}
		public ExpressionAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionAssignment(this);
		}
	}

	public final ExpressionAssignmentContext expressionAssignment() throws RecognitionException {
		ExpressionAssignmentContext _localctx = new ExpressionAssignmentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expressionAssignment);
		try {
			setState(261);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				((ExpressionAssignmentContext)_localctx).expr_lvalue = expressionOr();
				setState(254);
				match(T__18);
				setState(255);
				((ExpressionAssignmentContext)_localctx).expr_rvalue = expressionAssignment();

				             ((ExpressionAssignmentContext)_localctx).lvalue =  ((ExpressionAssignmentContext)_localctx).expr_lvalue.expr; ((ExpressionAssignmentContext)_localctx).rvalue =  ((ExpressionAssignmentContext)_localctx).expr_rvalue.expr; 
				             BinaryOperator bo = BinaryOperator.assign;
				             BinaryExpression be = new BinaryExpression(((ExpressionAssignmentContext)_localctx).expr_lvalue.expr, ((ExpressionAssignmentContext)_localctx).expr_rvalue.expr, bo);
				             ((ExpressionAssignmentContext)_localctx).expr =  be;
				         
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
				((ExpressionAssignmentContext)_localctx).exp = expressionOr();
				((ExpressionAssignmentContext)_localctx).expr =  ((ExpressionAssignmentContext)_localctx).exp.expr; ((ExpressionAssignmentContext)_localctx).rvalue =  null; ((ExpressionAssignmentContext)_localctx).lvalue =  null;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionOrContext extends ParserRuleContext {
		public Expression expr;
		public BinaryOperator be;
		public ExpressionAndContext lvalue;
		public ExpressionOrTempContext rvalue;
		public ExpressionAndContext expressionAnd() {
			return getRuleContext(ExpressionAndContext.class,0);
		}
		public ExpressionOrTempContext expressionOrTemp() {
			return getRuleContext(ExpressionOrTempContext.class,0);
		}
		public ExpressionOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionOr(this);
		}
	}

	public final ExpressionOrContext expressionOr() throws RecognitionException {
		ExpressionOrContext _localctx = new ExpressionOrContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expressionOr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			((ExpressionOrContext)_localctx).lvalue = expressionAnd();
			setState(264);
			((ExpressionOrContext)_localctx).rvalue = expressionOrTemp();

			            if(((ExpressionOrContext)_localctx).rvalue.expr != null){
			                ((ExpressionOrContext)_localctx).expr =  new BinaryExpression(((ExpressionOrContext)_localctx).lvalue.expr, ((ExpressionOrContext)_localctx).rvalue.expr, ((ExpressionOrContext)_localctx).rvalue.bo);
			            }
			            else{
			                ((ExpressionOrContext)_localctx).expr =  ((ExpressionOrContext)_localctx).lvalue.expr;
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionOrTempContext extends ParserRuleContext {
		public Expression expr;
		public BinaryOperator bo;
		public ExpressionAndContext lvalue;
		public ExpressionOrTempContext rvalue;
		public ExpressionAndContext expressionAnd() {
			return getRuleContext(ExpressionAndContext.class,0);
		}
		public ExpressionOrTempContext expressionOrTemp() {
			return getRuleContext(ExpressionOrTempContext.class,0);
		}
		public ExpressionOrTempContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionOrTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionOrTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionOrTemp(this);
		}
	}

	public final ExpressionOrTempContext expressionOrTemp() throws RecognitionException {
		ExpressionOrTempContext _localctx = new ExpressionOrTempContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_expressionOrTemp);
		try {
			setState(274);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__19:
				enterOuterAlt(_localctx, 1);
				{
				setState(267);
				match(T__19);
				((ExpressionOrTempContext)_localctx).bo =  BinaryOperator.or;
				setState(269);
				((ExpressionOrTempContext)_localctx).lvalue = expressionAnd();
				setState(270);
				((ExpressionOrTempContext)_localctx).rvalue = expressionOrTemp();
				   if(((ExpressionOrTempContext)_localctx).rvalue.expr != null)
				                ((ExpressionOrTempContext)_localctx).expr =  new BinaryExpression(((ExpressionOrTempContext)_localctx).lvalue.expr, ((ExpressionOrTempContext)_localctx).rvalue.expr, ((ExpressionOrTempContext)_localctx).rvalue.bo);
				            else
				                ((ExpressionOrTempContext)_localctx).expr =  ((ExpressionOrTempContext)_localctx).lvalue.expr;
				            
				}
				break;
			case T__4:
			case T__8:
			case T__12:
			case T__18:
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionAndContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionEqContext lvalue;
		public ExpressionAndTempContext rvalue;
		public ExpressionEqContext expressionEq() {
			return getRuleContext(ExpressionEqContext.class,0);
		}
		public ExpressionAndTempContext expressionAndTemp() {
			return getRuleContext(ExpressionAndTempContext.class,0);
		}
		public ExpressionAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionAnd(this);
		}
	}

	public final ExpressionAndContext expressionAnd() throws RecognitionException {
		ExpressionAndContext _localctx = new ExpressionAndContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expressionAnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			((ExpressionAndContext)_localctx).lvalue = expressionEq();
			setState(277);
			((ExpressionAndContext)_localctx).rvalue = expressionAndTemp();

			            if(((ExpressionAndContext)_localctx).rvalue.expr != null){
			                ((ExpressionAndContext)_localctx).expr =  new BinaryExpression(((ExpressionAndContext)_localctx).lvalue.expr, ((ExpressionAndContext)_localctx).rvalue.expr, ((ExpressionAndContext)_localctx).rvalue.bo);
			        }
			            else{
			                ((ExpressionAndContext)_localctx).expr =  ((ExpressionAndContext)_localctx).lvalue.expr;
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionAndTempContext extends ParserRuleContext {
		public Expression expr;
		public BinaryOperator bo;
		public ExpressionEqContext expr1;
		public ExpressionAndTempContext expr2;
		public ExpressionEqContext expressionEq() {
			return getRuleContext(ExpressionEqContext.class,0);
		}
		public ExpressionAndTempContext expressionAndTemp() {
			return getRuleContext(ExpressionAndTempContext.class,0);
		}
		public ExpressionAndTempContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionAndTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionAndTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionAndTemp(this);
		}
	}

	public final ExpressionAndTempContext expressionAndTemp() throws RecognitionException {
		ExpressionAndTempContext _localctx = new ExpressionAndTempContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_expressionAndTemp);
		try {
			setState(287);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				match(T__20);
				((ExpressionAndTempContext)_localctx).bo =  BinaryOperator.and;
				setState(282);
				((ExpressionAndTempContext)_localctx).expr1 = expressionEq();
				setState(283);
				((ExpressionAndTempContext)_localctx).expr2 = expressionAndTemp();
				   
				            if(((ExpressionAndTempContext)_localctx).expr2.expr != null)
				                ((ExpressionAndTempContext)_localctx).expr =  new BinaryExpression(((ExpressionAndTempContext)_localctx).expr1.expr, ((ExpressionAndTempContext)_localctx).expr2.expr, ((ExpressionAndTempContext)_localctx).expr2.bo);
				            else
				                ((ExpressionAndTempContext)_localctx).expr =  ((ExpressionAndTempContext)_localctx).expr1.expr;
				        
				}
				break;
			case T__4:
			case T__8:
			case T__12:
			case T__18:
			case T__19:
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionEqContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionCmpContext lvalue;
		public ExpressionEqTempContext rvalue;
		public ExpressionCmpContext expressionCmp() {
			return getRuleContext(ExpressionCmpContext.class,0);
		}
		public ExpressionEqTempContext expressionEqTemp() {
			return getRuleContext(ExpressionEqTempContext.class,0);
		}
		public ExpressionEqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionEq; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionEq(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionEq(this);
		}
	}

	public final ExpressionEqContext expressionEq() throws RecognitionException {
		ExpressionEqContext _localctx = new ExpressionEqContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expressionEq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			((ExpressionEqContext)_localctx).lvalue = expressionCmp();
			setState(290);
			((ExpressionEqContext)_localctx).rvalue = expressionEqTemp();

			            if(((ExpressionEqContext)_localctx).rvalue.expr != null){
			                ((ExpressionEqContext)_localctx).expr =  new BinaryExpression(((ExpressionEqContext)_localctx).lvalue.expr, ((ExpressionEqContext)_localctx).rvalue.expr, ((ExpressionEqContext)_localctx).rvalue.bo);
			        }
			            else{
			                ((ExpressionEqContext)_localctx).expr =  ((ExpressionEqContext)_localctx).lvalue.expr;
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionEqTempContext extends ParserRuleContext {
		public Expression expr;
		public BinaryOperator bo;
		public ExpressionCmpContext expr1;
		public ExpressionEqTempContext expr2;
		public ExpressionCmpContext expressionCmp() {
			return getRuleContext(ExpressionCmpContext.class,0);
		}
		public ExpressionEqTempContext expressionEqTemp() {
			return getRuleContext(ExpressionEqTempContext.class,0);
		}
		public ExpressionEqTempContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionEqTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionEqTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionEqTemp(this);
		}
	}

	public final ExpressionEqTempContext expressionEqTemp() throws RecognitionException {
		ExpressionEqTempContext _localctx = new ExpressionEqTempContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_expressionEqTemp);
		try {
			setState(304);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
			case T__22:
				enterOuterAlt(_localctx, 1);
				{
				setState(297);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__21:
					{
					setState(293);
					match(T__21);
					((ExpressionEqTempContext)_localctx).bo =  BinaryOperator.eq;
					}
					break;
				case T__22:
					{
					setState(295);
					match(T__22);
					((ExpressionEqTempContext)_localctx).bo =  BinaryOperator.neq;
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(299);
				((ExpressionEqTempContext)_localctx).expr1 = expressionCmp();
				setState(300);
				((ExpressionEqTempContext)_localctx).expr2 = expressionEqTemp();
				   
				            if(((ExpressionEqTempContext)_localctx).expr2.expr != null){
				                ((ExpressionEqTempContext)_localctx).expr =  new BinaryExpression(((ExpressionEqTempContext)_localctx).expr1.expr, ((ExpressionEqTempContext)_localctx).expr2.expr, ((ExpressionEqTempContext)_localctx).expr2.bo);}
				            else{
				                ((ExpressionEqTempContext)_localctx).expr =  ((ExpressionEqTempContext)_localctx).expr1.expr;
				            }
				        
				}
				break;
			case T__4:
			case T__8:
			case T__12:
			case T__18:
			case T__19:
			case T__20:
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionCmpContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionAddContext lvalue;
		public ExpressionCmpTempContext rvalue;
		public ExpressionAddContext expressionAdd() {
			return getRuleContext(ExpressionAddContext.class,0);
		}
		public ExpressionCmpTempContext expressionCmpTemp() {
			return getRuleContext(ExpressionCmpTempContext.class,0);
		}
		public ExpressionCmpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionCmp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionCmp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionCmp(this);
		}
	}

	public final ExpressionCmpContext expressionCmp() throws RecognitionException {
		ExpressionCmpContext _localctx = new ExpressionCmpContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_expressionCmp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			((ExpressionCmpContext)_localctx).lvalue = expressionAdd();
			setState(307);
			((ExpressionCmpContext)_localctx).rvalue = expressionCmpTemp();

			            if(((ExpressionCmpContext)_localctx).rvalue.expr != null){
			                ((ExpressionCmpContext)_localctx).expr =  new BinaryExpression(((ExpressionCmpContext)_localctx).lvalue.expr, ((ExpressionCmpContext)_localctx).rvalue.expr, ((ExpressionCmpContext)_localctx).rvalue.bo);
			        }
			            else{
			                ((ExpressionCmpContext)_localctx).expr =  ((ExpressionCmpContext)_localctx).lvalue.expr;
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionCmpTempContext extends ParserRuleContext {
		public Expression expr;
		public BinaryOperator bo;
		public ExpressionAddContext expr1;
		public ExpressionCmpTempContext expr2;
		public ExpressionAddContext expressionAdd() {
			return getRuleContext(ExpressionAddContext.class,0);
		}
		public ExpressionCmpTempContext expressionCmpTemp() {
			return getRuleContext(ExpressionCmpTempContext.class,0);
		}
		public ExpressionCmpTempContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionCmpTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionCmpTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionCmpTemp(this);
		}
	}

	public final ExpressionCmpTempContext expressionCmpTemp() throws RecognitionException {
		ExpressionCmpTempContext _localctx = new ExpressionCmpTempContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expressionCmpTemp);
		try {
			setState(321);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(314);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__23:
					{
					setState(310);
					match(T__23);
					((ExpressionCmpTempContext)_localctx).bo =  BinaryOperator.lt;
					}
					break;
				case T__24:
					{
					setState(312);
					match(T__24);
					((ExpressionCmpTempContext)_localctx).bo =  BinaryOperator.gt;
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(316);
				((ExpressionCmpTempContext)_localctx).expr1 = expressionAdd();
				setState(317);
				((ExpressionCmpTempContext)_localctx).expr2 = expressionCmpTemp();

				            if(((ExpressionCmpTempContext)_localctx).expr2.expr != null){
				                ((ExpressionCmpTempContext)_localctx).expr =  new BinaryExpression(((ExpressionCmpTempContext)_localctx).expr1.expr, ((ExpressionCmpTempContext)_localctx).expr2.expr, ((ExpressionCmpTempContext)_localctx).expr2.bo);
				            }
				            else{
				                ((ExpressionCmpTempContext)_localctx).expr =  ((ExpressionCmpTempContext)_localctx).expr1.expr;
				            }
				        
				}
				break;
			case T__4:
			case T__8:
			case T__12:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionAddContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionMultContext lvalue;
		public ExpressionAddTempContext rvalue;
		public ExpressionMultContext expressionMult() {
			return getRuleContext(ExpressionMultContext.class,0);
		}
		public ExpressionAddTempContext expressionAddTemp() {
			return getRuleContext(ExpressionAddTempContext.class,0);
		}
		public ExpressionAddContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionAdd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionAdd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionAdd(this);
		}
	}

	public final ExpressionAddContext expressionAdd() throws RecognitionException {
		ExpressionAddContext _localctx = new ExpressionAddContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_expressionAdd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			((ExpressionAddContext)_localctx).lvalue = expressionMult();
			setState(324);
			((ExpressionAddContext)_localctx).rvalue = expressionAddTemp();

			            if(((ExpressionAddContext)_localctx).rvalue.expr != null){
			                ((ExpressionAddContext)_localctx).expr =  new BinaryExpression(((ExpressionAddContext)_localctx).lvalue.expr, ((ExpressionAddContext)_localctx).rvalue.expr, ((ExpressionAddContext)_localctx).rvalue.bo);
			        }
			            else{
			                ((ExpressionAddContext)_localctx).expr =  ((ExpressionAddContext)_localctx).lvalue.expr;
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionAddTempContext extends ParserRuleContext {
		public Expression expr;
		public BinaryOperator bo;
		public ExpressionMultContext expr1;
		public ExpressionAddTempContext expr2;
		public ExpressionMultContext expressionMult() {
			return getRuleContext(ExpressionMultContext.class,0);
		}
		public ExpressionAddTempContext expressionAddTemp() {
			return getRuleContext(ExpressionAddTempContext.class,0);
		}
		public ExpressionAddTempContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionAddTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionAddTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionAddTemp(this);
		}
	}

	public final ExpressionAddTempContext expressionAddTemp() throws RecognitionException {
		ExpressionAddTempContext _localctx = new ExpressionAddTempContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_expressionAddTemp);
		try {
			setState(338);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__25:
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__25:
					{
					setState(327);
					match(T__25);
					 ((ExpressionAddTempContext)_localctx).bo =  BinaryOperator.add; 
					}
					break;
				case T__26:
					{
					setState(329);
					match(T__26);
					 ((ExpressionAddTempContext)_localctx).bo =  BinaryOperator.sub; 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(333);
				((ExpressionAddTempContext)_localctx).expr1 = expressionMult();
				setState(334);
				((ExpressionAddTempContext)_localctx).expr2 = expressionAddTemp();
				   
				            if(((ExpressionAddTempContext)_localctx).expr2.expr != null)
				                ((ExpressionAddTempContext)_localctx).expr =  new BinaryExpression(((ExpressionAddTempContext)_localctx).expr1.expr, ((ExpressionAddTempContext)_localctx).expr2.expr, ((ExpressionAddTempContext)_localctx).expr2.bo);
				            else
				                ((ExpressionAddTempContext)_localctx).expr =  ((ExpressionAddTempContext)_localctx).expr1.expr;

				            
				}
				break;
			case T__4:
			case T__8:
			case T__12:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionMultContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionUnaryContext lvalue;
		public ExpressionMultTempContext rvalue;
		public ExpressionUnaryContext expressionUnary() {
			return getRuleContext(ExpressionUnaryContext.class,0);
		}
		public ExpressionMultTempContext expressionMultTemp() {
			return getRuleContext(ExpressionMultTempContext.class,0);
		}
		public ExpressionMultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionMult; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionMult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionMult(this);
		}
	}

	public final ExpressionMultContext expressionMult() throws RecognitionException {
		ExpressionMultContext _localctx = new ExpressionMultContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_expressionMult);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			((ExpressionMultContext)_localctx).lvalue = expressionUnary();
			setState(341);
			((ExpressionMultContext)_localctx).rvalue = expressionMultTemp();

			            if(((ExpressionMultContext)_localctx).rvalue.expr != null){
			                ((ExpressionMultContext)_localctx).expr =  new BinaryExpression(((ExpressionMultContext)_localctx).lvalue.expr, ((ExpressionMultContext)_localctx).rvalue.expr, ((ExpressionMultContext)_localctx).rvalue.bo);
			            }
			            else{ ((ExpressionMultContext)_localctx).expr =  ((ExpressionMultContext)_localctx).lvalue.expr;}
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionMultTempContext extends ParserRuleContext {
		public Expression expr;
		public BinaryOperator bo;
		public ExpressionUnaryContext expr1;
		public ExpressionMultTempContext expr2;
		public ExpressionUnaryContext expressionUnary() {
			return getRuleContext(ExpressionUnaryContext.class,0);
		}
		public ExpressionMultTempContext expressionMultTemp() {
			return getRuleContext(ExpressionMultTempContext.class,0);
		}
		public ExpressionMultTempContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionMultTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionMultTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionMultTemp(this);
		}
	}

	public final ExpressionMultTempContext expressionMultTemp() throws RecognitionException {
		ExpressionMultTempContext _localctx = new ExpressionMultTempContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_expressionMultTemp);
		try {
			setState(355);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
			case T__28:
				enterOuterAlt(_localctx, 1);
				{
				setState(348);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__27:
					{
					setState(344);
					match(T__27);
					((ExpressionMultTempContext)_localctx).bo =  BinaryOperator.mult;
					}
					break;
				case T__28:
					{
					setState(346);
					match(T__28);
					((ExpressionMultTempContext)_localctx).bo =  BinaryOperator.div;
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(350);
				((ExpressionMultTempContext)_localctx).expr1 = expressionUnary();
				setState(351);
				((ExpressionMultTempContext)_localctx).expr2 = expressionMultTemp();
				   if(((ExpressionMultTempContext)_localctx).expr2.expr != null)
				                ((ExpressionMultTempContext)_localctx).expr =  new BinaryExpression(((ExpressionMultTempContext)_localctx).expr1.expr, ((ExpressionMultTempContext)_localctx).expr2.expr, ((ExpressionMultTempContext)_localctx).expr2.bo);
				            else
				                ((ExpressionMultTempContext)_localctx).expr =  ((ExpressionMultTempContext)_localctx).expr1.expr;
				            
				}
				break;
			case T__4:
			case T__8:
			case T__12:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionUnaryContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionUnaryContext exp;
		public ExpressionUnaryContext exp1;
		public ExpressionMemContext exp2;
		public ExpressionUnaryContext expressionUnary() {
			return getRuleContext(ExpressionUnaryContext.class,0);
		}
		public ExpressionMemContext expressionMem() {
			return getRuleContext(ExpressionMemContext.class,0);
		}
		public ExpressionUnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionUnary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionUnary(this);
		}
	}

	public final ExpressionUnaryContext expressionUnary() throws RecognitionException {
		ExpressionUnaryContext _localctx = new ExpressionUnaryContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_expressionUnary);
		try {
			setState(370);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__29:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(357);
				match(T__29);
				setState(358);
				((ExpressionUnaryContext)_localctx).exp = expressionUnary();
				}
				UnaryOperator uo = UnaryOperator.not; ((ExpressionUnaryContext)_localctx).expr =  new UnaryExpression(uo, ((ExpressionUnaryContext)_localctx).exp.expr);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(362);
				match(T__26);
				setState(363);
				((ExpressionUnaryContext)_localctx).exp1 = expressionUnary();
				}
				UnaryOperator uo1 = UnaryOperator.minus; ((ExpressionUnaryContext)_localctx).expr =  new UnaryExpression(uo1, ((ExpressionUnaryContext)_localctx).exp1.expr);
				}
				break;
			case T__3:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case CONST_NUM:
			case CONST_STR:
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(367);
				((ExpressionUnaryContext)_localctx).exp2 = expressionMem();
				((ExpressionUnaryContext)_localctx).expr =  ((ExpressionUnaryContext)_localctx).exp2.expr;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionMemContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionMethodsContext instance;
		public ExpressionMemTempContext index;
		public ExpressionMethodsContext expressionMethods() {
			return getRuleContext(ExpressionMethodsContext.class,0);
		}
		public ExpressionMemTempContext expressionMemTemp() {
			return getRuleContext(ExpressionMemTempContext.class,0);
		}
		public ExpressionMemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionMem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionMem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionMem(this);
		}
	}

	public final ExpressionMemContext expressionMem() throws RecognitionException {
		ExpressionMemContext _localctx = new ExpressionMemContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_expressionMem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			((ExpressionMemContext)_localctx).instance = expressionMethods();
			setState(373);
			((ExpressionMemContext)_localctx).index = expressionMemTemp();
			if(((ExpressionMemContext)_localctx).index.expr != null){
			             ((ExpressionMemContext)_localctx).expr =  new ArrayCall(((ExpressionMemContext)_localctx).instance.expr, ((ExpressionMemContext)_localctx).index.expr);
			            }
			        else{
			            ((ExpressionMemContext)_localctx).expr =  ((ExpressionMemContext)_localctx).instance.expr;
			        }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionMemTempContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionContext index;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionMemTempContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionMemTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionMemTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionMemTemp(this);
		}
	}

	public final ExpressionMemTempContext expressionMemTemp() throws RecognitionException {
		ExpressionMemTempContext _localctx = new ExpressionMemTempContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_expressionMemTemp);
		try {
			setState(382);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__30:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(T__30);
				setState(377);
				((ExpressionMemTempContext)_localctx).index = expression();
				setState(378);
				match(T__31);
				((ExpressionMemTempContext)_localctx).expr =  ((ExpressionMemTempContext)_localctx).index.expr;
				}
				break;
			case T__4:
			case T__8:
			case T__12:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
			case T__25:
			case T__26:
			case T__27:
			case T__28:
			case T__31:
				enterOuterAlt(_localctx, 2);
				{
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionMethodsContext extends ParserRuleContext {
		public Expression expr;
		public ExpressionOtherContext instance;
		public ExpressionMethodsTempContext methodcall;
		public ExpressionOtherContext expressionOther() {
			return getRuleContext(ExpressionOtherContext.class,0);
		}
		public ExpressionMethodsTempContext expressionMethodsTemp() {
			return getRuleContext(ExpressionMethodsTempContext.class,0);
		}
		public ExpressionMethodsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionMethods; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionMethods(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionMethods(this);
		}
	}

	public final ExpressionMethodsContext expressionMethods() throws RecognitionException {
		ExpressionMethodsContext _localctx = new ExpressionMethodsContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_expressionMethods);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			((ExpressionMethodsContext)_localctx).instance = expressionOther();
			setState(385);
			((ExpressionMethodsContext)_localctx).methodcall = expressionMethodsTemp(((ExpressionMethodsContext)_localctx).instance.expr);
			   
			            if(((ExpressionMethodsContext)_localctx).methodcall.methodcall == null){
			                ((ExpressionMethodsContext)_localctx).expr =  ((ExpressionMethodsContext)_localctx).instance.expr;
			            }
			            else if(((ExpressionMethodsContext)_localctx).instance.expr == null || ((ExpressionMethodsContext)_localctx).methodcall.methodcall != null){
			                ((ExpressionMethodsContext)_localctx).expr =  ((ExpressionMethodsContext)_localctx).methodcall.methodcall;
			            }
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionMethodsTempContext extends ParserRuleContext {
		public Expression instance;
		public Expression methodcall;
		public Token methodname;
		public ExpressionMethodsTempContext temp;
		public ExpressionContext arg;
		public TerminalNode ID() { return getToken(SmoolaParser.ID, 0); }
		public ExpressionMethodsTempContext expressionMethodsTemp() {
			return getRuleContext(ExpressionMethodsTempContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionMethodsTempContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExpressionMethodsTempContext(ParserRuleContext parent, int invokingState, Expression instance) {
			super(parent, invokingState);
			this.instance = instance;
		}
		@Override public int getRuleIndex() { return RULE_expressionMethodsTemp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionMethodsTemp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionMethodsTemp(this);
		}
	}

	public final ExpressionMethodsTempContext expressionMethodsTemp(Expression instance) throws RecognitionException {
		ExpressionMethodsTempContext _localctx = new ExpressionMethodsTempContext(_ctx, getState(), instance);
		enterRule(_localctx, 60, RULE_expressionMethodsTemp);
		int _la;
		try {
			setState(422);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(388);
				match(T__32);
				setState(389);
				((ExpressionMethodsTempContext)_localctx).methodname = match(ID);
				setState(390);
				match(T__3);
				setState(391);
				match(T__4);
				   
				                Identifier id = new Identifier((((ExpressionMethodsTempContext)_localctx).methodname!=null?((ExpressionMethodsTempContext)_localctx).methodname.getText():null));
				                MethodCall new_inst = new MethodCall(_localctx.instance, id);
				            
				setState(393);
				((ExpressionMethodsTempContext)_localctx).temp = expressionMethodsTemp(new_inst);
				((ExpressionMethodsTempContext)_localctx).methodcall =  ((ExpressionMethodsTempContext)_localctx).temp.methodcall;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(396);
				match(T__32);
				setState(397);
				((ExpressionMethodsTempContext)_localctx).methodname = match(ID);
				setState(398);
				match(T__3);

				                Identifier id = new Identifier((((ExpressionMethodsTempContext)_localctx).methodname!=null?((ExpressionMethodsTempContext)_localctx).methodname.getText():null));
				                MethodCall tempm = new MethodCall(_localctx.instance, id);
				            
				{
				setState(400);
				((ExpressionMethodsTempContext)_localctx).arg = expression();
				tempm.addArg(((ExpressionMethodsTempContext)_localctx).arg.expr);
				setState(408);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__12) {
					{
					{
					setState(402);
					match(T__12);
					setState(403);
					((ExpressionMethodsTempContext)_localctx).arg = expression();
					tempm.addArg(((ExpressionMethodsTempContext)_localctx).arg.expr);
					}
					}
					setState(410);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				setState(411);
				match(T__4);
				setState(412);
				((ExpressionMethodsTempContext)_localctx).temp = expressionMethodsTemp(tempm);
				((ExpressionMethodsTempContext)_localctx).methodcall =  ((ExpressionMethodsTempContext)_localctx).temp.methodcall;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(415);
				match(T__32);
				setState(416);
				match(T__33);
				Length new_inst = new Length(_localctx.instance); 
				setState(418);
				((ExpressionMethodsTempContext)_localctx).temp = expressionMethodsTemp(new_inst);
				((ExpressionMethodsTempContext)_localctx).methodcall =  ((ExpressionMethodsTempContext)_localctx).temp.methodcall;
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				((ExpressionMethodsTempContext)_localctx).methodcall =  _localctx.instance;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionOtherContext extends ParserRuleContext {
		public Expression expr;
		public Expression lvalue;
		public Expression rvalue;
		public Token num;
		public Token str;
		public Token ln;
		public Token name;
		public Token constval;
		public Token id;
		public ExpressionContext exp;
		public ExpressionContext thisexpr;
		public TerminalNode CONST_NUM() { return getToken(SmoolaParser.CONST_NUM, 0); }
		public TerminalNode CONST_STR() { return getToken(SmoolaParser.CONST_STR, 0); }
		public TerminalNode ID() { return getToken(SmoolaParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionOtherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionOther; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterExpressionOther(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitExpressionOther(this);
		}
	}

	public final ExpressionOtherContext expressionOther() throws RecognitionException {
		ExpressionOtherContext _localctx = new ExpressionOtherContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_expressionOther);
		try {
			setState(458);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(424);
				((ExpressionOtherContext)_localctx).num = match(CONST_NUM);
				   
				                IntType t = new IntType();
				                Expression temp_expr = new IntValue((((ExpressionOtherContext)_localctx).num!=null?Integer.valueOf(((ExpressionOtherContext)_localctx).num.getText()):0), t);
				                ((ExpressionOtherContext)_localctx).expr =  temp_expr;
				            
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(426);
				((ExpressionOtherContext)_localctx).str = match(CONST_STR);

				            StringType st = new StringType();
				            ((ExpressionOtherContext)_localctx).expr =  new StringValue((((ExpressionOtherContext)_localctx).str!=null?((ExpressionOtherContext)_localctx).str.getText():null), st);
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(428);
				((ExpressionOtherContext)_localctx).ln = match(T__34);
				setState(429);
				match(T__6);
				setState(430);
				match(T__30);
				setState(431);
				((ExpressionOtherContext)_localctx).num = match(CONST_NUM);
				setState(432);
				match(T__31);
				   
				                IntType t = new IntType();
				                IntValue val = new IntValue((((ExpressionOtherContext)_localctx).num!=null?Integer.valueOf(((ExpressionOtherContext)_localctx).num.getText()):0), t);
				                NewArray newarr = new NewArray();
				                newarr.setExpression(val);
				                newarr.setSize((((ExpressionOtherContext)_localctx).num!=null?Integer.valueOf(((ExpressionOtherContext)_localctx).num.getText()):0));
				                newarr.setLine(((ExpressionOtherContext)_localctx).ln.getLine());
				                ((ExpressionOtherContext)_localctx).expr =  newarr;
				            
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(434);
				match(T__34);
				setState(435);
				((ExpressionOtherContext)_localctx).name = match(ID);
				setState(436);
				match(T__3);
				setState(437);
				match(T__4);

				            Identifier id = new Identifier((((ExpressionOtherContext)_localctx).name!=null?((ExpressionOtherContext)_localctx).name.getText():null));
				            ((ExpressionOtherContext)_localctx).expr =  new NewClass(id);
				            
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(439);
				match(T__35);
				 ((ExpressionOtherContext)_localctx).expr =  new This();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(441);
				((ExpressionOtherContext)_localctx).constval = match(T__36);

				                                BooleanType bt = new BooleanType(); 
				                                ((ExpressionOtherContext)_localctx).expr =  new BooleanValue(true, bt); 
				                             
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(443);
				((ExpressionOtherContext)_localctx).constval = match(T__37);
				 BooleanType bt = new BooleanType();
				                                ((ExpressionOtherContext)_localctx).expr =  new BooleanValue(false, bt);
				                                
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(445);
				((ExpressionOtherContext)_localctx).id = match(ID);
				((ExpressionOtherContext)_localctx).expr =  new Identifier((((ExpressionOtherContext)_localctx).id!=null?((ExpressionOtherContext)_localctx).id.getText():null));
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(447);
				((ExpressionOtherContext)_localctx).id = match(ID);
				setState(448);
				match(T__30);
				setState(449);
				((ExpressionOtherContext)_localctx).exp = expression();
				setState(450);
				match(T__31);
				     
				                Identifier identifier = new Identifier((((ExpressionOtherContext)_localctx).id!=null?((ExpressionOtherContext)_localctx).id.getText():null));
				                ((ExpressionOtherContext)_localctx).expr =  new ArrayCall(identifier, ((ExpressionOtherContext)_localctx).exp.expr);
				            
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(453);
				match(T__3);
				setState(454);
				((ExpressionOtherContext)_localctx).thisexpr = expression();
				setState(455);
				match(T__4);

				                ((ExpressionOtherContext)_localctx).lvalue =  ((ExpressionOtherContext)_localctx).thisexpr.lvalue;
				                ((ExpressionOtherContext)_localctx).rvalue =  ((ExpressionOtherContext)_localctx).thisexpr.rvalue;
				                ((ExpressionOtherContext)_localctx).expr =  ((ExpressionOtherContext)_localctx).thisexpr.expr;
				            
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Type t;
		public Token id;
		public TerminalNode ID() { return getToken(SmoolaParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SmoolaListener ) ((SmoolaListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_type);
		try {
			setState(472);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(460);
				match(T__6);
				((TypeContext)_localctx).t =  new IntType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(462);
				match(T__38);
				((TypeContext)_localctx).t =  new BooleanType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(464);
				match(T__39);
				((TypeContext)_localctx).t =  new StringType();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(466);
				match(T__6);
				setState(467);
				match(T__30);
				setState(468);
				match(T__31);
				((TypeContext)_localctx).t =  new ArrayType();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(470);
				((TypeContext)_localctx).id = match(ID);

				                 Identifier cid = new Identifier((((TypeContext)_localctx).id!=null?((TypeContext)_localctx).id.getText():null)); 
				                 UserDefinedType ct = new UserDefinedType();
				                 ct.setName(cid);
				                 ((TypeContext)_localctx).t =  ct;
				                 incompleteTypes.add(ct);
				                 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\60\u01dd\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\7\2H\n\2\f\2\16\2K\13\2\3\2\3\2\3\2\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4n\n\4\3\4\3\4\3\4\3\4\3\4"+
		"\7\4u\n\4\f\4\16\4x\13\4\3\4\3\4\3\4\7\4}\n\4\f\4\16\4\u0080\13\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00a0\n\6\f\6\16\6"+
		"\u00a3\13\6\3\6\3\6\5\6\u00a7\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00b0"+
		"\n\6\f\6\16\6\u00b3\13\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\7\7\u00c1\n\7\f\7\16\7\u00c4\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00d5\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00e8\n\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0108\n\17\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0115\n\21\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0122\n\23\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\5\25\u012c\n\25\3\25\3\25\3\25\3\25"+
		"\3\25\5\25\u0133\n\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\5\27\u013d"+
		"\n\27\3\27\3\27\3\27\3\27\3\27\5\27\u0144\n\27\3\30\3\30\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\5\31\u014e\n\31\3\31\3\31\3\31\3\31\3\31\5\31\u0155\n"+
		"\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\5\33\u015f\n\33\3\33\3\33"+
		"\3\33\3\33\3\33\5\33\u0166\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\5\34\u0175\n\34\3\35\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\5\36\u0181\n\36\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \7 \u0199\n \f \16 \u019c\13 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \5 \u01a9\n \3!\3!\3!\3!\3!\3!\3!\3!"+
		"\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!"+
		"\3!\3!\3!\5!\u01cd\n!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\5\"\u01db\n\"\3\"\2\2#\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*"+
		",.\60\62\64\668:<>@B\2\2\2\u01e7\2D\3\2\2\2\4O\3\2\2\2\6g\3\2\2\2\b\u0084"+
		"\3\2\2\2\n\u008c\3\2\2\2\f\u00bc\3\2\2\2\16\u00d4\3\2\2\2\20\u00d6\3\2"+
		"\2\2\22\u00dc\3\2\2\2\24\u00eb\3\2\2\2\26\u00f2\3\2\2\2\30\u00f8\3\2\2"+
		"\2\32\u00fc\3\2\2\2\34\u0107\3\2\2\2\36\u0109\3\2\2\2 \u0114\3\2\2\2\""+
		"\u0116\3\2\2\2$\u0121\3\2\2\2&\u0123\3\2\2\2(\u0132\3\2\2\2*\u0134\3\2"+
		"\2\2,\u0143\3\2\2\2.\u0145\3\2\2\2\60\u0154\3\2\2\2\62\u0156\3\2\2\2\64"+
		"\u0165\3\2\2\2\66\u0174\3\2\2\28\u0176\3\2\2\2:\u0180\3\2\2\2<\u0182\3"+
		"\2\2\2>\u01a8\3\2\2\2@\u01cc\3\2\2\2B\u01da\3\2\2\2DE\b\2\1\2EI\5\4\3"+
		"\2FH\5\6\4\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2"+
		"\2LM\7\2\2\3MN\b\2\1\2N\3\3\2\2\2OP\7\3\2\2PQ\b\3\1\2QR\b\3\1\2RS\7.\2"+
		"\2ST\b\3\1\2TU\7\4\2\2UV\7\5\2\2VW\b\3\1\2WX\7.\2\2XY\7\6\2\2YZ\7\7\2"+
		"\2Z[\7\b\2\2[\\\7\t\2\2\\]\b\3\1\2]^\7\4\2\2^_\5\f\7\2_`\b\3\1\2`a\7\n"+
		"\2\2ab\5\32\16\2bc\b\3\1\2cd\7\13\2\2de\7\f\2\2ef\7\f\2\2f\5\3\2\2\2g"+
		"h\b\4\1\2hi\7\3\2\2ij\b\4\1\2jm\7.\2\2kl\7\r\2\2ln\7.\2\2mk\3\2\2\2mn"+
		"\3\2\2\2no\3\2\2\2op\b\4\1\2pv\7\4\2\2qr\5\b\5\2rs\b\4\1\2su\3\2\2\2t"+
		"q\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w~\3\2\2\2xv\3\2\2\2yz\5\n\6\2"+
		"z{\b\4\1\2{}\3\2\2\2|y\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177"+
		"\u0081\3\2\2\2\u0080~\3\2\2\2\u0081\u0082\7\f\2\2\u0082\u0083\b\4\1\2"+
		"\u0083\7\3\2\2\2\u0084\u0085\7\16\2\2\u0085\u0086\b\5\1\2\u0086\u0087"+
		"\7.\2\2\u0087\u0088\7\b\2\2\u0088\u0089\5B\"\2\u0089\u008a\7\13\2\2\u008a"+
		"\u008b\b\5\1\2\u008b\t\3\2\2\2\u008c\u008d\7\5\2\2\u008d\u008e\b\6\1\2"+
		"\u008e\u008f\7.\2\2\u008f\u00a6\b\6\1\2\u0090\u0091\7\6\2\2\u0091\u00a7"+
		"\7\7\2\2\u0092\u0093\7\6\2\2\u0093\u0094\b\6\1\2\u0094\u0095\7.\2\2\u0095"+
		"\u0096\7\b\2\2\u0096\u0097\5B\"\2\u0097\u00a1\b\6\1\2\u0098\u0099\7\17"+
		"\2\2\u0099\u009a\b\6\1\2\u009a\u009b\7.\2\2\u009b\u009c\7\b\2\2\u009c"+
		"\u009d\5B\"\2\u009d\u009e\b\6\1\2\u009e\u00a0\3\2\2\2\u009f\u0098\3\2"+
		"\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\u00a4\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a5\7\7\2\2\u00a5\u00a7\3\2"+
		"\2\2\u00a6\u0090\3\2\2\2\u00a6\u0092\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8"+
		"\u00a9\7\b\2\2\u00a9\u00aa\5B\"\2\u00aa\u00ab\7\4\2\2\u00ab\u00b1\b\6"+
		"\1\2\u00ac\u00ad\5\b\5\2\u00ad\u00ae\b\6\1\2\u00ae\u00b0\3\2\2\2\u00af"+
		"\u00ac\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\5\f\7\2\u00b5"+
		"\u00b6\b\6\1\2\u00b6\u00b7\7\n\2\2\u00b7\u00b8\5\32\16\2\u00b8\u00b9\b"+
		"\6\1\2\u00b9\u00ba\7\13\2\2\u00ba\u00bb\7\f\2\2\u00bb\13\3\2\2\2\u00bc"+
		"\u00c2\b\7\1\2\u00bd\u00be\5\16\b\2\u00be\u00bf\b\7\1\2\u00bf\u00c1\3"+
		"\2\2\2\u00c0\u00bd\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2"+
		"\u00c3\3\2\2\2\u00c3\r\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\5\20\t"+
		"\2\u00c6\u00c7\b\b\1\2\u00c7\u00d5\3\2\2\2\u00c8\u00c9\5\22\n\2\u00c9"+
		"\u00ca\b\b\1\2\u00ca\u00d5\3\2\2\2\u00cb\u00cc\5\24\13\2\u00cc\u00cd\b"+
		"\b\1\2\u00cd\u00d5\3\2\2\2\u00ce\u00cf\5\26\f\2\u00cf\u00d0\b\b\1\2\u00d0"+
		"\u00d5\3\2\2\2\u00d1\u00d2\5\30\r\2\u00d2\u00d3\b\b\1\2\u00d3\u00d5\3"+
		"\2\2\2\u00d4\u00c5\3\2\2\2\u00d4\u00c8\3\2\2\2\u00d4\u00cb\3\2\2\2\u00d4"+
		"\u00ce\3\2\2\2\u00d4\u00d1\3\2\2\2\u00d5\17\3\2\2\2\u00d6\u00d7\7\4\2"+
		"\2\u00d7\u00d8\b\t\1\2\u00d8\u00d9\5\f\7\2\u00d9\u00da\b\t\1\2\u00da\u00db"+
		"\7\f\2\2\u00db\21\3\2\2\2\u00dc\u00dd\7\20\2\2\u00dd\u00de\7\6\2\2\u00de"+
		"\u00df\5\32\16\2\u00df\u00e0\7\7\2\2\u00e0\u00e1\7\21\2\2\u00e1\u00e2"+
		"\5\16\b\2\u00e2\u00e7\b\n\1\2\u00e3\u00e4\7\22\2\2\u00e4\u00e5\5\16\b"+
		"\2\u00e5\u00e6\b\n\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e3\3\2\2\2\u00e7\u00e8"+
		"\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\b\n\1\2\u00ea\23\3\2\2\2\u00eb"+
		"\u00ec\7\23\2\2\u00ec\u00ed\7\6\2\2\u00ed\u00ee\5\32\16\2\u00ee\u00ef"+
		"\7\7\2\2\u00ef\u00f0\5\16\b\2\u00f0\u00f1\b\13\1\2\u00f1\25\3\2\2\2\u00f2"+
		"\u00f3\7\24\2\2\u00f3\u00f4\5\32\16\2\u00f4\u00f5\7\7\2\2\u00f5\u00f6"+
		"\7\13\2\2\u00f6\u00f7\b\f\1\2\u00f7\27\3\2\2\2\u00f8\u00f9\5\32\16\2\u00f9"+
		"\u00fa\7\13\2\2\u00fa\u00fb\b\r\1\2\u00fb\31\3\2\2\2\u00fc\u00fd\5\34"+
		"\17\2\u00fd\u00fe\b\16\1\2\u00fe\33\3\2\2\2\u00ff\u0100\5\36\20\2\u0100"+
		"\u0101\7\25\2\2\u0101\u0102\5\34\17\2\u0102\u0103\b\17\1\2\u0103\u0108"+
		"\3\2\2\2\u0104\u0105\5\36\20\2\u0105\u0106\b\17\1\2\u0106\u0108\3\2\2"+
		"\2\u0107\u00ff\3\2\2\2\u0107\u0104\3\2\2\2\u0108\35\3\2\2\2\u0109\u010a"+
		"\5\"\22\2\u010a\u010b\5 \21\2\u010b\u010c\b\20\1\2\u010c\37\3\2\2\2\u010d"+
		"\u010e\7\26\2\2\u010e\u010f\b\21\1\2\u010f\u0110\5\"\22\2\u0110\u0111"+
		"\5 \21\2\u0111\u0112\b\21\1\2\u0112\u0115\3\2\2\2\u0113\u0115\3\2\2\2"+
		"\u0114\u010d\3\2\2\2\u0114\u0113\3\2\2\2\u0115!\3\2\2\2\u0116\u0117\5"+
		"&\24\2\u0117\u0118\5$\23\2\u0118\u0119\b\22\1\2\u0119#\3\2\2\2\u011a\u011b"+
		"\7\27\2\2\u011b\u011c\b\23\1\2\u011c\u011d\5&\24\2\u011d\u011e\5$\23\2"+
		"\u011e\u011f\b\23\1\2\u011f\u0122\3\2\2\2\u0120\u0122\3\2\2\2\u0121\u011a"+
		"\3\2\2\2\u0121\u0120\3\2\2\2\u0122%\3\2\2\2\u0123\u0124\5*\26\2\u0124"+
		"\u0125\5(\25\2\u0125\u0126\b\24\1\2\u0126\'\3\2\2\2\u0127\u0128\7\30\2"+
		"\2\u0128\u012c\b\25\1\2\u0129\u012a\7\31\2\2\u012a\u012c\b\25\1\2\u012b"+
		"\u0127\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012e\5*"+
		"\26\2\u012e\u012f\5(\25\2\u012f\u0130\b\25\1\2\u0130\u0133\3\2\2\2\u0131"+
		"\u0133\3\2\2\2\u0132\u012b\3\2\2\2\u0132\u0131\3\2\2\2\u0133)\3\2\2\2"+
		"\u0134\u0135\5.\30\2\u0135\u0136\5,\27\2\u0136\u0137\b\26\1\2\u0137+\3"+
		"\2\2\2\u0138\u0139\7\32\2\2\u0139\u013d\b\27\1\2\u013a\u013b\7\33\2\2"+
		"\u013b\u013d\b\27\1\2\u013c\u0138\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u013e"+
		"\3\2\2\2\u013e\u013f\5.\30\2\u013f\u0140\5,\27\2\u0140\u0141\b\27\1\2"+
		"\u0141\u0144\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u013c\3\2\2\2\u0143\u0142"+
		"\3\2\2\2\u0144-\3\2\2\2\u0145\u0146\5\62\32\2\u0146\u0147\5\60\31\2\u0147"+
		"\u0148\b\30\1\2\u0148/\3\2\2\2\u0149\u014a\7\34\2\2\u014a\u014e\b\31\1"+
		"\2\u014b\u014c\7\35\2\2\u014c\u014e\b\31\1\2\u014d\u0149\3\2\2\2\u014d"+
		"\u014b\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0150\5\62\32\2\u0150\u0151\5"+
		"\60\31\2\u0151\u0152\b\31\1\2\u0152\u0155\3\2\2\2\u0153\u0155\3\2\2\2"+
		"\u0154\u014d\3\2\2\2\u0154\u0153\3\2\2\2\u0155\61\3\2\2\2\u0156\u0157"+
		"\5\66\34\2\u0157\u0158\5\64\33\2\u0158\u0159\b\32\1\2\u0159\63\3\2\2\2"+
		"\u015a\u015b\7\36\2\2\u015b\u015f\b\33\1\2\u015c\u015d\7\37\2\2\u015d"+
		"\u015f\b\33\1\2\u015e\u015a\3\2\2\2\u015e\u015c\3\2\2\2\u015f\u0160\3"+
		"\2\2\2\u0160\u0161\5\66\34\2\u0161\u0162\5\64\33\2\u0162\u0163\b\33\1"+
		"\2\u0163\u0166\3\2\2\2\u0164\u0166\3\2\2\2\u0165\u015e\3\2\2\2\u0165\u0164"+
		"\3\2\2\2\u0166\65\3\2\2\2\u0167\u0168\7 \2\2\u0168\u0169\5\66\34\2\u0169"+
		"\u016a\3\2\2\2\u016a\u016b\b\34\1\2\u016b\u0175\3\2\2\2\u016c\u016d\7"+
		"\35\2\2\u016d\u016e\5\66\34\2\u016e\u016f\3\2\2\2\u016f\u0170\b\34\1\2"+
		"\u0170\u0175\3\2\2\2\u0171\u0172\58\35\2\u0172\u0173\b\34\1\2\u0173\u0175"+
		"\3\2\2\2\u0174\u0167\3\2\2\2\u0174\u016c\3\2\2\2\u0174\u0171\3\2\2\2\u0175"+
		"\67\3\2\2\2\u0176\u0177\5<\37\2\u0177\u0178\5:\36\2\u0178\u0179\b\35\1"+
		"\2\u01799\3\2\2\2\u017a\u017b\7!\2\2\u017b\u017c\5\32\16\2\u017c\u017d"+
		"\7\"\2\2\u017d\u017e\b\36\1\2\u017e\u0181\3\2\2\2\u017f\u0181\3\2\2\2"+
		"\u0180\u017a\3\2\2\2\u0180\u017f\3\2\2\2\u0181;\3\2\2\2\u0182\u0183\5"+
		"@!\2\u0183\u0184\5> \2\u0184\u0185\b\37\1\2\u0185=\3\2\2\2\u0186\u0187"+
		"\7#\2\2\u0187\u0188\7.\2\2\u0188\u0189\7\6\2\2\u0189\u018a\7\7\2\2\u018a"+
		"\u018b\b \1\2\u018b\u018c\5> \2\u018c\u018d\b \1\2\u018d\u01a9\3\2\2\2"+
		"\u018e\u018f\7#\2\2\u018f\u0190\7.\2\2\u0190\u0191\7\6\2\2\u0191\u0192"+
		"\b \1\2\u0192\u0193\5\32\16\2\u0193\u019a\b \1\2\u0194\u0195\7\17\2\2"+
		"\u0195\u0196\5\32\16\2\u0196\u0197\b \1\2\u0197\u0199\3\2\2\2\u0198\u0194"+
		"\3\2\2\2\u0199\u019c\3\2\2\2\u019a\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019b"+
		"\u019d\3\2\2\2\u019c\u019a\3\2\2\2\u019d\u019e\7\7\2\2\u019e\u019f\5>"+
		" \2\u019f\u01a0\b \1\2\u01a0\u01a9\3\2\2\2\u01a1\u01a2\7#\2\2\u01a2\u01a3"+
		"\7$\2\2\u01a3\u01a4\b \1\2\u01a4\u01a5\5> \2\u01a5\u01a6\b \1\2\u01a6"+
		"\u01a9\3\2\2\2\u01a7\u01a9\b \1\2\u01a8\u0186\3\2\2\2\u01a8\u018e\3\2"+
		"\2\2\u01a8\u01a1\3\2\2\2\u01a8\u01a7\3\2\2\2\u01a9?\3\2\2\2\u01aa\u01ab"+
		"\7+\2\2\u01ab\u01cd\b!\1\2\u01ac\u01ad\7,\2\2\u01ad\u01cd\b!\1\2\u01ae"+
		"\u01af\7%\2\2\u01af\u01b0\7\t\2\2\u01b0\u01b1\7!\2\2\u01b1\u01b2\7+\2"+
		"\2\u01b2\u01b3\7\"\2\2\u01b3\u01cd\b!\1\2\u01b4\u01b5\7%\2\2\u01b5\u01b6"+
		"\7.\2\2\u01b6\u01b7\7\6\2\2\u01b7\u01b8\7\7\2\2\u01b8\u01cd\b!\1\2\u01b9"+
		"\u01ba\7&\2\2\u01ba\u01cd\b!\1\2\u01bb\u01bc\7\'\2\2\u01bc\u01cd\b!\1"+
		"\2\u01bd\u01be\7(\2\2\u01be\u01cd\b!\1\2\u01bf\u01c0\7.\2\2\u01c0\u01cd"+
		"\b!\1\2\u01c1\u01c2\7.\2\2\u01c2\u01c3\7!\2\2\u01c3\u01c4\5\32\16\2\u01c4"+
		"\u01c5\7\"\2\2\u01c5\u01c6\b!\1\2\u01c6\u01cd\3\2\2\2\u01c7\u01c8\7\6"+
		"\2\2\u01c8\u01c9\5\32\16\2\u01c9\u01ca\7\7\2\2\u01ca\u01cb\b!\1\2\u01cb"+
		"\u01cd\3\2\2\2\u01cc\u01aa\3\2\2\2\u01cc\u01ac\3\2\2\2\u01cc\u01ae\3\2"+
		"\2\2\u01cc\u01b4\3\2\2\2\u01cc\u01b9\3\2\2\2\u01cc\u01bb\3\2\2\2\u01cc"+
		"\u01bd\3\2\2\2\u01cc\u01bf\3\2\2\2\u01cc\u01c1\3\2\2\2\u01cc\u01c7\3\2"+
		"\2\2\u01cdA\3\2\2\2\u01ce\u01cf\7\t\2\2\u01cf\u01db\b\"\1\2\u01d0\u01d1"+
		"\7)\2\2\u01d1\u01db\b\"\1\2\u01d2\u01d3\7*\2\2\u01d3\u01db\b\"\1\2\u01d4"+
		"\u01d5\7\t\2\2\u01d5\u01d6\7!\2\2\u01d6\u01d7\7\"\2\2\u01d7\u01db\b\""+
		"\1\2\u01d8\u01d9\7.\2\2\u01d9\u01db\b\"\1\2\u01da\u01ce\3\2\2\2\u01da"+
		"\u01d0\3\2\2\2\u01da\u01d2\3\2\2\2\u01da\u01d4\3\2\2\2\u01da\u01d8\3\2"+
		"\2\2\u01dbC\3\2\2\2\35Imv~\u00a1\u00a6\u00b1\u00c2\u00d4\u00e7\u0107\u0114"+
		"\u0121\u012b\u0132\u013c\u0143\u014d\u0154\u015e\u0165\u0174\u0180\u019a"+
		"\u01a8\u01cc\u01da";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}