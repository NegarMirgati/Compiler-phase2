package ast.node;

import ast.Visitor;

public abstract class Node {
    protected int lineNumber;
    public void accept(Visitor visitor) {}
    public int getLine() {return this.lineNumber;}
    public void setLine(int line_) {this.lineNumber = line_ ;}
}
