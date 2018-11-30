package ast.node.expression;

import ast.Visitor;

public class NewArray extends Expression {
    private Expression expression;
    private int size;

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public void setSize(int size_){
        this.size = size_;
    }
    public int Size(){
        return this.size;
    }
    @Override
    public String toString() {
        return "NewArray";
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
