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

public class VisitorImpl implements Visitor {

    @Override
    public void visit(Program program) {
        program.getMainClass().accept(this);
        ArrayList <ClassDeclaration> classes = new ArrayList<>(program.getClasses());
        for(int i = 0; i < classes.size(); i++){
            classes.get(i).accept(this);
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
        System.out.print("vardec : ");
        System.out.println(varDeclaration.getIdentifier().toString());
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
        System.out.println(newClass.toString());
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
        conditional.getExpression().accept(this);
        conditional.getConsequenceBody().accept(this);
        conditional.getAlternativeBody().accept(this);
    }

    @Override
    public void visit(While loop) {
              // is that all?
              System.out.println("loop");
              loop.getCondition().accept(this);
              System.out.println("khakha");
              loop.getBody().accept(this);
    }

    @Override
    public void visit(Write write) {
        System.out.println("write");
        write.getArg().accept(this); // is that all ? 
    }
}
