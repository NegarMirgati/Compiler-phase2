package ast;
package symbolTable;

import ast.node.Node;
import ast.node.Program;
import ast.node.declaration.ClassDeclaration;
import ast.node.declaration.MainMethodDeclaration;
import ast.node.declaration.MethodDeclaration;
import ast.node.declaration.VarDeclaration;
import ast.node.expression.*;
import ast.node.expression.Value.BooleanValue;
import ast.node.expression.Value.IntValue;
import ast.node.expression.Value.StringValue;
import ast.node.statement.*;
import symbolTable.*;

public class VisitorImpl implements Visitor {
    
    public void check_method_by_symtable(ClassDeclaration classDeclaration){
        //todo for check method
        classDeclaration.getMethodDeclarations().accept(this);
    }
    public void check_inside_classes(Program program){
        ClassDeclaration main_class=program.getMainClass();
        List<ClassDeclaration> classes= program.getClasses();

        main_class.accept(this);
        //symbol table
        for(int i=0;i<classes;i++){
            //to do for symbol table 
            classes.get(i).accept(this);
        }
    }
    
    @Override
    public void visit(Node node) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(Program program) {
        //TODO: implement appropriate visit functionality
        check_inside_classes(program);
        
    }
    //declaration
    @Override
    public void visit(ClassDeclaration classDeclaration) {
        //TODO: implement appropriate visit functionality
        SymbolTable sym = new SymbolTable();//for symbol table
        symtable.push(sym);
        check_method_by_symtable(classDeclaration);
        //
        //for main class, what happen??
        symtable.pop();

    }

    @Override
    public void visit(MethodDeclaration methodDeclaration) {
   
        methodDeclaration.getReturnType().accept(this); // is that all ????
        
    }

    @Override
    public void visit(MainMethodDeclaration mainMethodDeclaration) {
        //TODO: implement appropriate visit functionality
        mainMethodDeclaration.getReturnValue().getType().accept(this);  //not sure
    }

    @Override
    public void visit(VarDeclaration varDeclaration) {
        //TODO: implement appropriate visit functionality
        varDeclaration.getIdentifier().accept(this);
        varDeclaration.getType().accept(this);
    }
    //Expressions
    @Override
    public void visit(ArrayCall arrayCall) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(BinaryExpression binaryExpression) {
        System.out.println(binaryExpression.toString());
        binaryExpression.getLeft().accept(this);
        binaryExpression.getRight().accept(this);
    }

    @Override
    public void visit(Identifier identifier) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(Length length) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(MethodCall methodCall) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(NewArray newArray) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(NewClass newClass) {
        //TODO: implement appropriate visit functionality
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
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(IntValue value) {
        //TODO: implement appropriate visit functionality
        System.out.println(value.toString());
    }

    @Override
    public void visit(StringValue value) {
        //TODO: implement appropriate visit functionality
    }
    //Statements
    @Override
    public void visit(Assign assign) {
        assign.getlValue().accept(this);
        assign.getrValue().accept(this); // is that all ? 

    }

    @Override
    public void visit(Block block) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(Conditional conditional) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(While loop) {
        //TODO: implement appropriate visit functionality
    }

    @Override
    public void visit(Write write) {

        write.getArg().accept(this); // is that all ? 
    }
}
