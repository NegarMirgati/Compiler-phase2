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

public class VisitorImpl implements Visitor {

    private int numPassedRounds = 0;
    private Boolean hasErrors = false;
    private SymbolTable symTable ;
    public int number_of_repeated_method=0;
    public int index_variable =0;

    /*public void putGlobalVar(String name , Type type) throws ItemAlreadyExistsException{
        try {
            SymbolTable.top.put( new SymbolTableVariableItem(name,type,index_variable++));
            }
            catch(ItemAlreadyExistsException e) {
            print(String.format("[Line #%s] Variable \"%s\" already exists.", name.getLine(), name));
            
            }
        
        print(name + " " + type.toString() );
    }*/

    public void put_method(String name, ArrayList<VarDeclaration> argTypes)throws ItemAlreadyExistsException{
        ArrayList<Type>types = new ArrayList<Type>();
        for(int i=0;i<argTypes.size(); i++){
            types.add(argTypes.get(i).getType());
        }
        SymbolTable.top.put(new SymbolTableMethodItem(name,types));
    }
    public void check_method_name(String methodname, ArrayList<VarDeclaration> argTypes)throws ItemAlreadyExistsException{
        try{
            put_method(methodname,argTypes);
        }catch(ItemAlreadyExistsException e){
            //print(String.format("[Line #%s] Variable \"%s\" already exists.", $methodname.getLine(), methodname));
            System.out.println(String.format(" Variable \"%s\" already exists.", methodname));
            String new_name = methodname + "Temporary_" + Integer.toString(number_of_repeated_method);
            number_of_repeated_method+=1;
            try{
            put_method(new_name,argTypes);
            }
            catch(ItemAlreadyExistsException ee){}
        }  
    }

    public void checkForNameErrors(){

        System.out.println("searching");
    }

    @Override
    public void visit(Program program) {
        if(numPassedRounds == 0){
            symTable = new SymbolTable();
            hasErrors = false;
            checkForNameErrors();

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
        System.out.print("Class Declaration");
        System.out.println(classDeclaration.getName());
        ArrayList<MethodDeclaration> mthds = new ArrayList<>(classDeclaration.getMethodDeclarations());
        for (int i = 0; i < mthds.size(); i++){
            mthds.get(i).accept(this);
        }

        ArrayList<VarDeclaration> vards = new ArrayList<>(classDeclaration.getVarDeclarations());
        for (int i = 0; i < vards.size(); i++){
            vards.get(i).accept(this);
        }

    }

    @Override
    public void visit(MethodDeclaration methodDeclaration) {
        System.out.print("method : ");
        System.out.print(methodDeclaration.getName());
        System.out.println(methodDeclaration.getReturnType().toString()); // is that all ????
        
        ArrayList<VarDeclaration>args = new ArrayList<VarDeclaration>();
        args= methodDeclaration.getArgs();

        ArrayList<Statement> body = methodDeclaration.getBody();
        for(int i = 0; i < body.size(); i++){
            body.get(i).accept(this);
        }

        ArrayList<VarDeclaration> vards = new ArrayList<>(methodDeclaration.getLocalVars());
        for (int i = 0; i < vards.size(); i++){
             vards.get(i).accept(this);
        }
        
    }

    @Override
    public void visit(VarDeclaration varDeclaration) {
        System.out.print("vardec : ");
        System.out.print(varDeclaration.getIdentifier().toString());
        System.out.println(varDeclaration.getType().toString());
        varDeclaration.getIdentifier().accept(this);
        System.out.println(varDeclaration.getType().toString());
    }

    @Override
    public void visit(ArrayCall arrayCall) {
        System.out.println("here");
        arrayCall.getIndex().accept(this);
        System.out.println("there");
        arrayCall.getInstance().accept(this);
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
        length.getExpression().accept(this);
    }

    @Override
    public void visit(MethodCall methodCall) {
        System.out.print("methodCall : ");
        System.out.println(methodCall.getMethodName());
        methodCall.getInstance().accept(this);
        ArrayList<Expression> args = new ArrayList<> (methodCall.getArgs());
        for(int i = 0; i < args.size(); i++){
            args.get(i).accept(this);
        }
    }

    @Override
    public void visit(NewArray newArray) {
        newArray.getExpression().accept(this);
    }

    @Override
    public void visit(NewClass newClass) {
        System.out.print("new class : ");
        System.out.println(newClass.getClassName());
    }

    @Override
    public void visit(This instance) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(UnaryExpression unaryExpression) {
        unaryExpression.getValue().accept(this); // is that all ?
    }

    @Override
    public void visit(BooleanValue value) {
        System.out.println(value.toString());
    }

    @Override
    public void visit(IntValue value) {
        System.out.println(value.toString());
        
    }

    @Override
    public void visit(StringValue value) {
        System.out.println(value.toString());
    }

    @Override
    public void visit(Assign assign) {
        assign.getlValue().accept(this);
        if(assign.getrValue() != null)
            assign.getrValue().accept(this); // is that all ? 
    }

    @Override
    public void visit(Block block) {
        System.out.println("block");
        // is that all ? is that correct? 
        ArrayList<Statement> bb = new ArrayList<> (block.getBody());
        for(int i = 0; i < bb.size(); i++){
            bb.get(i).accept(this);
        }
    }

    @Override
    public void visit(Conditional conditional) {
        System.out.println("conditional");
        System.out.println(conditional.getExpression().toString());
        conditional.getExpression().accept(this);
        conditional.getConsequenceBody().accept(this);
        if(conditional.getAlternativeBody() != null )
            conditional.getAlternativeBody().accept(this);
    }

    @Override
    public void visit(While loop) {
              // is that all?
              System.out.println("loop");
              loop.getCondition().accept(this);
              loop.getBody().accept(this);
    }

    @Override
    public void visit(Write write) {
        System.out.println("write");
        write.getArg().accept(this); // is that all ? 
    }
}
