package ast;

import ast.node.Program;
import ast.node.declaration.ClassDeclaration;
import ast.node.declaration.MethodDeclaration;
import ast.node.declaration.VarDeclaration;
import ast.node.expression.*;
import ast.node.expression.Value.BooleanValue;
import ast.node.expression.Value.IntValue;
import ast.node.expression.Value.StringValue;
import ast.node.statement.*;
import java.util.ArrayList;
import java.lang.*;
import symbolTable.*;
import ast.Type.*;
import ast.Type.UserDefinedType.*;

public class VisitorImpl implements Visitor {

    private int numPassedRounds = 0;
    private Boolean hasErrors = false;
    private SymbolTable symTable;
    public int number_of_repeated_method=0;
    public int index_variable =0;
    public int index_class =0;

    public void putGlobalVar(String name , Type type) throws ItemAlreadyExistsException{
        
        SymbolTable.top.put( new SymbolTableVariableItem(name,type,index_variable));
    }

    public void checkVariableName(VarDeclaration varDeclaration, int ClassFirstLine, int parentLine){
        String name = varDeclaration.getIdentifier().getName();
        Type type=varDeclaration.getType();
        index_variable += 1;
        try {
                putGlobalVar(name,type);

            }catch(ItemAlreadyExistsException e) {
                hasErrors = true;
                if(varDeclaration.getLine() > ClassFirstLine){
                    //System.out.println(String.format("parent line : %d , this line : %d",parentLine, varDeclaration.getLine()));
                    int lineToShow = Math.max(varDeclaration.getLine(), parentLine);
                    System.out.println(String.format("Line:%d:Redefinition of Variable %s", lineToShow, name));
                }
                String new_name = name + "Temporary_" + Integer.toString(index_variable);
            
            try{
                putGlobalVar(name,type);
            
            }catch(ItemAlreadyExistsException ee){}
            }
    }
    public void put_class(String name,Type type)throws ItemAlreadyExistsException{

        SymbolTable.top.put( new SymbolTableClassItem(name,type,index_class));
    }

    public void checkClassNames(ClassDeclaration classDeclaration){
        String name= classDeclaration.getName().getName();
        UserDefinedType class_type= new UserDefinedType();
        class_type.setClassDeclaration(classDeclaration);
        index_class+=1;

        try{
            put_class(name, class_type);
        }catch(ItemAlreadyExistsException e){

            System.out.println(String.format("Line:%d:Redefinition of class %s", classDeclaration.getLine(), name));
            hasErrors = true;
            String new_name = name + "Temporary_" + Integer.toString(index_class);
            
            try{
                put_class(name,class_type);
            }
            catch(ItemAlreadyExistsException ee){}
        }
    }

    public void put_method(String name, ArrayList<VarDeclaration> argTypes)throws ItemAlreadyExistsException{
        ArrayList<Type>types = new ArrayList<Type>();
        for(int i=0;i<argTypes.size(); i++){
            types.add(argTypes.get(i).getType());
        }
        SymbolTable.top.put(new SymbolTableMethodItem(name,types));
    }
    public void checkMethodName(MethodDeclaration methodDeclaration, int classFirstLine, int parentLine){
        String methodname = methodDeclaration.getName().getName();
        ArrayList<VarDeclaration> argTypes = new ArrayList<>(methodDeclaration.getArgs());
        argTypes= methodDeclaration.getArgs();
        try{
            put_method(methodname,argTypes);
        }catch(ItemAlreadyExistsException e){
            if(methodDeclaration.getLine() > classFirstLine){
                hasErrors = true;
                int lineToShow = Math.max(methodDeclaration.getLine(), parentLine);
                System.out.println(String.format("Line:%d:Redefinition of method %s", lineToShow, methodname));
            }
            String new_name = methodname + "Temporary_" + Integer.toString(number_of_repeated_method);
            number_of_repeated_method+=1;
            try{
            put_method(new_name,argTypes);
            }
            catch(ItemAlreadyExistsException ee){}
        }  
    }

    public int getLineOfParentMethod(String methodname, String parentName, Program program){

        ClassDeclaration pc = findParentClass(parentName, program);
        if(pc == null)
            return -1;
        ArrayList<MethodDeclaration> methods = pc.getMethodDeclarations();
        for(int i = 0 ; i < methods.size(); i++){
            //System.out.println(String.format("this name %s , parent name %s", methodname, methods.get(i).getName().getName()));
            if(methods.get(i).getName().getName().equals(methodname))
                return methods.get(i).getLine();
        }
        return -1;
    }

    public int getLineOfParentVar(String varName, String parentName, Program program){

        ClassDeclaration pc = findParentClass(parentName, program);
        if(pc == null)
            return -1;
        ArrayList<VarDeclaration> vars = pc.getVarDeclarations();
        for(int i = 0 ; i < vars.size(); i++){
            if(vars.get(i).getIdentifier().getName().equals(varName))
                return vars.get(i).getLine();
        }
        return -1;
    }

    public void checkAllClassesForRepeatedNameErrors(Program program){

        ArrayList<ClassDeclaration> classDecs = new ArrayList<> ();
        classDecs.add(program.getMainClass());
        classDecs.addAll(program.getClasses());
        symTable.top = (new SymbolTable());
        for(int i = 0 ; i < classDecs.size(); i++){
            checkClassNames(classDecs.get(i));
        }
        symTable.top.pop();
    }

    public void checkInsideClass(ClassDeclaration cd, Program program){
        checkVariableNamesInsideClass(cd, program);
        checkMethodNamesInsideClass(cd, program);
        checkInsideMethods(cd, program);
    }

    public void checkVariableNamesInsideClass(ClassDeclaration cd, Program program){
        symTable.top = (new SymbolTable());
        int classDecLine = cd.getLine();
        
        ArrayList<VarDeclaration> variableDecs = new ArrayList<> ();

        if(cd.getParentName() != null){
            String parentName = cd.getParentName().getName();
            ClassDeclaration pc = findParentClass(parentName, program);
            if(pc != null)
                variableDecs.addAll(pc.getVarDeclarations());
        }
       
        variableDecs.addAll(cd.getVarDeclarations());

        for(int i = 0; i < variableDecs.size(); i++){
            String varName = variableDecs.get(i).getIdentifier().getName();
            int parentLine = -1;
            if(cd.getParentName() != null)
                parentLine = getLineOfParentVar(varName, cd.getParentName().getName(), program);

            checkVariableName(variableDecs.get(i), classDecLine, parentLine);

        }
        symTable.top.pop();
    }
    public void checkMethodNamesInsideClass(ClassDeclaration cd, Program program){

        symTable.top = (new SymbolTable());
        int classDecLine = cd.getLine();
        
        ArrayList<MethodDeclaration> methodDecs = new ArrayList<> ();

        if(cd.getParentName() != null){
            String parentName = cd.getParentName().getName();
            ClassDeclaration pc = findParentClass(parentName, program);
            if(pc!= null)
                methodDecs.addAll(pc.getMethodDeclarations());
        }
       
        methodDecs.addAll(cd.getMethodDeclarations());

        for(int i = 0; i < methodDecs.size(); i++){
            String varName = methodDecs.get(i).getName().getName();
            int parentLine = -1;
            if(cd.getParentName() != null){
                parentLine = getLineOfParentMethod(varName, cd.getParentName().getName(), program);
            }

            checkMethodName(methodDecs.get(i), classDecLine, parentLine);

        }
        symTable.top.pop();

    }
    public void checkVariablesOfMethod(ArrayList<VarDeclaration> variableDecs, Program program){
        symTable.top = (new SymbolTable());

        for(int i = 0; i < variableDecs.size(); i++){
            String varName = variableDecs.get(i).getIdentifier().getName();
            checkVariableName(variableDecs.get(i), -1, -1);
        }
        symTable.top.pop();
    }
   
    public void checkInsideMethods(ClassDeclaration md, Program p){
        ArrayList<MethodDeclaration> methodDecs = md.getMethodDeclarations();
        for(int i = 0; i < methodDecs.size(); i++){
            checkVariablesOfMethod(methodDecs.get(i).getArgs(), p);
            checkVariablesOfMethod(methodDecs.get(i).getLocalVars(), p);
            checkForInvalidIndexOfNewArray(methodDecs.get(i), p);
        }
    }
    
    public void checkForInvalidIndexOfNewArray(MethodDeclaration md, Program p){
            visit(md);
    }

    public ClassDeclaration findParentClass(String parentName, Program p){
        ArrayList <ClassDeclaration> classDecs = getAllClassDeclarations(p);
        for(int i = 0; i  < classDecs.size(); i++){
            if(classDecs.get(i).getName().getName().equals(parentName)){
                return classDecs.get(i);
            }
        }   
        return null;
    }

    public ArrayList<ClassDeclaration> getAllClassDeclarations(Program p){

        ArrayList<ClassDeclaration> classDecs = new ArrayList<> ();
        classDecs.add(p.getMainClass());
        classDecs.addAll(p.getClasses());
        return classDecs;
    }

    @Override
    public void visit(Program program) {
        if(numPassedRounds == 0){ // pass 1
            this.symTable = new SymbolTable();
            hasErrors = false;
            checkAllClassesForRepeatedNameErrors(program);
            numPassedRounds += 1;
        }
        if(numPassedRounds == 1){  // pass 2
            ArrayList<ClassDeclaration> classDecs = getAllClassDeclarations(program);
            for(int i = 0; i < classDecs.size(); i++){
                checkInsideClass(classDecs.get(i), program);
            }
            numPassedRounds += 1;
        }
        if(numPassedRounds == 2){ // final round : print ast if no errors found
            if(hasErrors == false)
                System.out.println(program.toString());
            ArrayList<ClassDeclaration> allClasses =  getAllClassDeclarations(program);
            for(int i = 0; i < allClasses.size(); i++){
                allClasses.get(i).accept(this);
            }
        }
    }

    @Override
    public void visit(ClassDeclaration classDeclaration) {
        
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(classDeclaration.toString());

        classDeclaration.getName().accept(this);
        if(classDeclaration.getParentName() != null)
            classDeclaration.getParentName().accept(this);
            
        ArrayList<VarDeclaration> vards = new ArrayList<>(classDeclaration.getVarDeclarations());
        for (int i = 0; i < vards.size(); i++){
            vards.get(i).accept(this);
        }
        ArrayList<MethodDeclaration> mthds = new ArrayList<>(classDeclaration.getMethodDeclarations());
        for (int i = 0; i < mthds.size(); i++){
            mthds.get(i).accept(this);
        }
    }

    @Override
    public void visit(MethodDeclaration methodDeclaration) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(methodDeclaration.toString());
   
        methodDeclaration.getName().accept(this); 

        ArrayList<VarDeclaration> args = methodDeclaration.getArgs();
        for(int i = 0 ; i < args.size(); i++)
            args.get(i).accept(this);

        //if(hasErrors== false && numPassedRounds == 2)
        //    System.out.println(methodDeclaration.getReturnType().toString());

        // accept local variables
        ArrayList <VarDeclaration> localVars = new ArrayList<>(methodDeclaration.getLocalVars());
        for(int i = 0; i < localVars.size(); i++){
            localVars.get(i).accept(this);
        }
        // then accept body statements 
        ArrayList<Statement> bodyStms = new ArrayList<>(methodDeclaration.getBody());
        for (int i = 0; i < bodyStms.size(); i++){
            bodyStms.get(i).accept(this);
        }
        // finally accept return statement
        methodDeclaration.getReturnValue().accept(this);
    }

    @Override
    public void visit(VarDeclaration varDeclaration) {

        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(varDeclaration.toString());

        varDeclaration.getIdentifier().accept(this);

        //if(hasErrors== false && numPassedRounds == 2)
        //    System.out.println(varDeclaration.getType().toString());
    }

    @Override
    public void visit(ArrayCall arrayCall) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(arrayCall.toString());

        arrayCall.getInstance().accept(this);
        arrayCall.getIndex().accept(this);
    }

    @Override
    public void visit(BinaryExpression binaryExpression) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(binaryExpression.toString());
        binaryExpression.getLeft().accept(this);
        binaryExpression.getRight().accept(this);
    }

    @Override
    public void visit(Identifier identifier) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(identifier.toString());
    }

    @Override
    public void visit(Length length) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(length.toString());
        length.getExpression().accept(this);
    }

    @Override
    public void visit(MethodCall methodCall) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(methodCall.toString());

        methodCall.getInstance().accept(this);
        methodCall.getMethodName().accept(this);
        ArrayList<Expression> args = new ArrayList<> (methodCall.getArgs());
        for(int i = 0; i < args.size(); i++){
            args.get(i).accept(this);
        }
    }

    @Override
    public void visit(NewArray newArray) {

        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(newArray.toString());

        if(newArray.Size() <= 0 && numPassedRounds == 2){          
            newArray.setSize(0);
            int line = newArray.getLine();
            System.out.println(String.format("Line:%d:Array length should not be zero or negative", line));
            hasErrors = true;
        }
   
        newArray.getExpression().accept(this);
    }

    @Override
    public void visit(NewClass newClass) {
        if(hasErrors== false && numPassedRounds == 2){
            System.out.println(newClass.toString());
            System.out.println(newClass.getClassName());
        }
    }

    @Override
    public void visit(This instance) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(UnaryExpression unaryExpression) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(unaryExpression.toString());
        unaryExpression.getValue().accept(this);
        
    }

    @Override
    public void visit(BooleanValue value) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(value.toString());
    }

    @Override
    public void visit(IntValue value) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(value.toString());
    }

    @Override
    public void visit(StringValue value) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(value.toString());
    }

    @Override
    public void visit(Assign assign) {

        if(assign.getrValue() == null){
            if(hasErrors== false && numPassedRounds == 2)
                System.out.println(assign.toString());  // not sure
            assign.getlValue().accept(this);
        }

        if(assign.getrValue() != null){
                if(hasErrors== false && numPassedRounds == 2)
                    System.out.println(assign.toString());
                assign.getlValue().accept(this);
                assign.getrValue().accept(this); 
            }
    }

    @Override
    public void visit(Block block) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(block.toString());

        ArrayList<Statement> bb = new ArrayList<> (block.getBody());
        for(int i = 0; i < bb.size(); i++){
            bb.get(i).accept(this);
        }
    }

    @Override
    public void visit(Conditional conditional) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(conditional.toString());

        conditional.getExpression().accept(this);
        conditional.getConsequenceBody().accept(this);

        if(conditional.getAlternativeBody() != null )
            conditional.getAlternativeBody().accept(this);
    }

    @Override
    public void visit(While loop) {
             if(hasErrors== false && numPassedRounds == 2)
                System.out.println(loop.toString());

              loop.getCondition().accept(this);
              loop.getBody().accept(this);
    }

    @Override
    public void visit(Write write) {
        if(hasErrors== false && numPassedRounds == 2)
            System.out.println(write.toString());

        write.getArg().accept(this); 
    }
}
