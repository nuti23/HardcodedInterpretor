package master.model.statement.Heap;


import master.collection.dictionary.MyIDictionary;
import master.model.exceptions.InvalidHeapAddressException;
import master.model.exceptions.StatementExecutionException;
import master.model.expression.Expression;
import master.model.ProgramState;
import master.model.statement.IStatement;

public class WriteStatement implements IStatement {
    private String var_name;
    private Expression expression;

    public WriteStatement(String var_name, Expression expression){
        this.expression = expression;
        this.var_name = var_name;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setVar_name(String var_name) {
        this.var_name = var_name;
    }

    public String getVar_name() {
        return var_name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeap();
        Integer address;

        if(!symbolTable.containsKey(var_name)){
            throw new StatementExecutionException("Variable " + var_name + " not found");
        }

        address = symbolTable.get(var_name);

        if(!heapTable.containsKey(address)){
            throw new InvalidHeapAddressException("Invalid address " + address);
        }

        heapTable.put(address, expression.evaluate(symbolTable, heapTable));
        state.setHeap(heapTable);
        return null;
    }

    @Override
    public String toString() {
        return "wH( " + var_name + ", " + expression.toString() + ") ";
    }
}
