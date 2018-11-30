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

public class VisitorImpl implements Visitor {

    private int numPassedRounds = 0;
    private Boolean hasErrors = false;
    private SymbolTable symTable ;

    public void checkForRepeatedNameErrors(){

        System.out.println("searching");
    }

    @Override
    public void visit(Program program) {
        if(numPassedRounds == 0){
            symTable = new SymbolTable();
            hasErrors = false;
            checkForRepeatedNameErrors();

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
        varDeclaration.getIdentifier().accept(this);
        System.out.println(varDeclaration.toString());
    }

    @Override
    public void visit(ArrayCall arrayCall) {
        arrayCall.getIndex().accept(this);
        arrayCall.getInstance().accept(this);
        System.out.println(arraycall.toString());
    }

    @Override
    public void visit(BinaryExpression binaryExpression) {
        binaryExpression.getLeft().accept(this);
        binaryExpression.getRight().accept(this);
        System.out.println(binaryExpression.toString());
    }

    @Override
    public void visit(Identifier identifier) {
        System.out.println(identifier.toString());
    }

    @Override
    public void visit(Length length) {
        length.getExpression().accept(this);
        System.out.println(length.toString());
    }

    @Override
    public void visit(MethodCall methodCall) {
        methodCall.getInstance().accept(this);
        ArrayList<Expression> args = new ArrayList<> (methodCall.getArgs());
        for(int i = 0; i < args.size(); i++){
            args.get(i).accept(this);
        }
        System.out.println(methodCall.toString());
    }

    @Override
    public void visit(NewArray newArray) {
        newArray.getExpression().accept(this); // do we need this?
        if(newArray.getExpression() == 0) // is that all?
            System.out.println("ErrorItemMessage: Array length should not be zero or negative");
        else
            System.out.println(newArray.toString());
    }

    @Override
    public void visit(NewClass newClass) {
        System.out.println(newClass.toString());
    }

    @Override
    public void visit(This instance) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(UnaryExpression unaryExpression) {
        unaryExpression.getValue().accept(this); // is that all ?
        System.out.println(unaryExpression.toString());
    }

    @Override
    public void visit(BooleanValue value) {
        if(hasErrors = false)
            System.out.println(value.toString());
    }

    @Override
    public void visit(IntValue value) {
        if(hasErrors = false)
            System.out.println(value.toString());
        
    }

    @Override
    public void visit(StringValue value) {
        if(hasErrors == false)
            System.out.println(value.toString());
    }

    @Override
    public void visit(Assign assign) {
        assign.getlValue().accept(this);
        if(assign.getrValue() != null)
            assign.getrValue().accept(this); // is that all ?

        System.out.println(assign.toString());
    }

    @Override
    public void visit(Block block) {
        ArrayList<Statement> bb = new ArrayList<> (block.getBody());
        for(int i = 0; i < bb.size(); i++){
            bb.get(i).accept(this);
        }
        System.out.println(block.toString());
    }

    @Override
    public void visit(Conditional conditional) {
        conditional.getExpression().accept(this);
        conditional.getConsequenceBody().accept(this);
        if(conditional.getAlternativeBody() != null )
            conditional.getAlternativeBody().accept(this);

        System.out.println(conditional.toString());
    }

    @Override
    public void visit(While loop) {
              // is that all?
              loop.getCondition().accept(this);
              loop.getBody().accept(this);
              System.out.println(loop.toString());
    }

    @Override
    public void visit(Write write) {
        write.getArg().accept(this); // is that all ? 
        System.out.println(write.toString());
    }
}
