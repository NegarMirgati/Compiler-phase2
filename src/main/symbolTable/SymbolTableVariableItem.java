package symbolTable;

import ast.Type.Type;

public class SymbolTableVariableItem extends SymbolTableVariableItemBase {

    

    public SymbolTableVariableItem(String name, Type type, int index) {
        this.name = name;
        this.type = type;
        this.index = index;
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