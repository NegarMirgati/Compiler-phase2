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
    private SymbolTable symTable ;
    public int number_of_repeated_method=0;
    public int index_variable =0;
    public int index_class =0;
    public void putGlobalVar(String name , Type type) throws ItemAlreadyExistsException{
        SymbolTable.top.put( new SymbolTableVariableItem(name,type,index_variable));

        
        // System.out.println(name + " " + type.toString() );
    }
    public void check_variable_name(VarDeclaration varDeclaration){
        String name= varDeclaration.getIdentifier().getName();
        Type type=varDeclaration.getType();
        index_variable+=1;
        try {
            putGlobalVar(name,type);
            

            }catch(ItemAlreadyExistsException e) {
            System.out.println(String.format("Line:[Line #%s]:Redefinition of variable \"%s\"", varDeclaration.getLine(), name));
            
            String new_name = name + "Temporary_" + Integer.toString(index_variable);
            
            try{
                putGlobalVar(name,type);
            
            }catch(ItemAlreadyExistsException ee){}
            }
    }
    public void put_class(String name,Type type)throws ItemAlreadyExistsException{
        SymbolTable.top.put( new SymbolTableClassItem(name,type,index_class));
    }
    public void check_class_name(ClassDeclaration classDeclaration){
        String name= classDeclaration.getName().getName();
        UserDefinedType class_type= new UserDefinedType();
        class_type.setClassDeclaration(classDeclaration);
        Type type= class_type;
        index_class+=1;
        try{
            put_class(name,type);
        }catch(ItemAlreadyExistsException e){

            System.out.println(String.format("Line:[Line #%s]:Redefinition of class\"%s\"", classDeclaration.getLine(), name));
            
            String new_name = name + "Temporary_" + Integer.toString(index_class);
            
            try{
                put_class(name,type);
            
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
    public void check_method_name(MethodDeclaration methodDeclaration){
        String methodname = methodDeclaration.getName().getName();
        ArrayList<VarDeclaration> argTypes = new ArrayList<>(methodDeclaration.getArgs());
        argTypes= methodDeclaration.getArgs();
        try{
            put_method(methodname,argTypes);
        }catch(ItemAlreadyExistsException e){
            print(String.format("Line:[Line #%s]:Redefinition of method\"%s\"", methodDeclaration.getLine(), methodname));
            
            String new_name = methodname + "Temporary_" + Integer.toString(number_of_repeated_method);
            number_of_repeated_method+=1;
            try{
            put_method(new_name,argTypes);
            }
            catch(ItemAlreadyExistsException ee){}
        }  
    }


    public void checkForRepeatedNameErrors(Program p){

        ArrayList<ClassDeclaration> classDecs = new ArrayList<>(p.getClasses());
       /* for(int i = 0 ; i < classDecs.size(); i++){
            try{
                
                symbolTable.top.put();
            }
            catch(ItemAlreadyExistsException e){
                String s = "Redefinition of class " ;
                s.concat(className);
                String line = "Line:";
                int classLine = classDecs.get(i).getLine();
                line.concat(Integer.toString(classLine));
                System.out.println(line.concat(s));

                hasErrors = True ;

                try{

                }
                catch(ItemAlreadyExistsException ee){
                    // nothing to do, never going to happen
                }
            }
            
        } */
    }

    @Override
    public void visit(Program program) {
        if(numPassedRounds == 0){
            symTable = new SymbolTable();
            hasErrors = false;
            //checkForRepeatedNameErrors(program);

        }
        if(hasErrors == false){  // first round completed time to start accepting each class
            numPassedRounds += 1;
            program.getMainClass().accept(this);
            ArrayList <ClassDeclaration> classes = new ArrayList<>(program.getClasses());
            for(int i = 0; i < classes.size(); i++){
                classes.get(i).accept(this);
            }
        }
    }

    @Override
    public void visit(ClassDeclaration classDeclaration) {

        System.out.println(classDeclaration.toString());

        classDeclaration.getName().accept(this);
        if(classDeclaration.getParentName() != null)
            classDeclaration.getParentName().accept(this);
            
        ArrayList<VarDeclaration> vards = new ArrayList<>(classDeclaration.getVarDeclarations());
        check_class_name(classDeclaration);
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

        System.out.println(methodDeclaration.toString());
   
        methodDeclaration.getName().accept(this); 

        
        check_method_name(methodDeclaration);
        for(int i = 0; i < args.size(); i++){
            args.get(i).accept(this);
        }

        System.out.println(methodDeclaration.getReturnType().toString());

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
        
        System.out.println(varDeclaration.toString());
        check_variable_name(varDeclaration);
        varDeclaration.getIdentifier().accept(this);
        System.out.println(varDeclaration.getType().toString());
    }

    @Override
    public void visit(ArrayCall arrayCall) {

        System.out.println(arrayCall.toString());

        arrayCall.getInstance().accept(this);
        arrayCall.getIndex().accept(this);
    }

    @Override
    public void visit(BinaryExpression binaryExpression) {
        System.out.println(binaryExpression.toString());
        binaryExpression.getLeft().accept(this);
        binaryExpression.getRight().accept(this);
    }

    @Override
    public void visit(Identifier identifier) {
        System.out.println(identifier.toString());
    }

    @Override
    public void visit(Length length) {
        System.out.println(length.toString());
        length.getExpression().accept(this);
    }

    @Override
    public void visit(MethodCall methodCall) {

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
        newArray.getExpression().accept(this); // do we need this?
        //if(newArray.getExpression().getConstant() == 0) // is that all?
          //  System.out.println("ErrorItemMessage: Array length should not be zero or negative");
        //else
            System.out.println(newArray.toString());
            newArray.getExpression().accept(this);
    }

    @Override
    public void visit(NewClass newClass) {

        System.out.println(newClass.toString());
        System.out.println(newClass.getClassName());
    }

    @Override
    public void visit(This instance) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(UnaryExpression unaryExpression) {

        System.out.println(unaryExpression.toString());
        unaryExpression.getValue().accept(this);
        
    }

    @Override
    public void visit(BooleanValue value) {
        //if(hasErrors = false)
            System.out.println(value.toString());
    }

    @Override
    public void visit(IntValue value) {
        //if(hasErrors = false)
            System.out.println(value.toString());
    }

    @Override
    public void visit(StringValue value) {
        //if(hasErrors == false)
            System.out.println(value.toString());
    }

    @Override
    public void visit(Assign assign) {

        if(assign.getrValue() == null){
            //System.out.println(assign.toString());  // not sure
            assign.getlValue().accept(this);
        }

        if(assign.getrValue() != null){
                System.out.println(assign.toString());
                assign.getlValue().accept(this);
                assign.getrValue().accept(this); 
            }
    }

    @Override
    public void visit(Block block) {
        System.out.println(block.toString());

        ArrayList<Statement> bb = new ArrayList<> (block.getBody());
        for(int i = 0; i < bb.size(); i++){
            bb.get(i).accept(this);
        }
        
    }

    @Override
    public void visit(Conditional conditional) {

        System.out.println(conditional.toString());

        conditional.getExpression().accept(this);
        conditional.getConsequenceBody().accept(this);

        if(conditional.getAlternativeBody() != null )
            conditional.getAlternativeBody().accept(this);
    }

    @Override
    public void visit(While loop) {
              System.out.println(loop.toString());

              loop.getCondition().accept(this);
              loop.getBody().accept(this);
    }

    @Override
    public void visit(Write write) {
        System.out.println(write.toString());

        write.getArg().accept(this); 
    }
}
