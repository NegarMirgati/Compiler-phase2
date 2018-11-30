package symbolTable;

import ast.Type.Type;

public abstract class SymbolTableVariableItemBase extends SymbolTableItem {

    protected int index;
    protected Type type;

    public SymbolTableVariableItemBase() {
       /* this.name = name;
        this.type = type;
        this.index = index;*/
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String getKey() {
        return name;
    }

    public int getIndex() {
        return index;
    }


}
