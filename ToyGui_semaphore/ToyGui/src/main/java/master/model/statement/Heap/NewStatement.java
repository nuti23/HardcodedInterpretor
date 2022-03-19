package master.model.statement.Heap;


import master.collection.dictionary.MyIDictionary;
import master.model.expression.Expression;
import master.model.ProgramState;
import master.model.statement.IStatement;

public class NewStatement implements IStatement {
    private String var_name;
    private Expression expression;

    public NewStatement(String var_name, Expression expression){
        this.expression = expression;
        this.var_name = var_name;
    }

    @Override
    public String toString(){
        return "new( " + var_name + ", " + expression + " )";
    }


    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeap();

        Integer heapAddress = state.getNewAddress();

        heapTable.put(heapAddress, expression.evaluate(symbolTable, heapTable));

        symbolTable.put(var_name, heapAddress);
        state.setSymbolTable(symbolTable);
        state.setHeap(heapTable);
        return null;
    }

    public String getVar_name() {
        return var_name;
    }

    public void setVar_name(String var_name) {
        this.var_name = var_name;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
