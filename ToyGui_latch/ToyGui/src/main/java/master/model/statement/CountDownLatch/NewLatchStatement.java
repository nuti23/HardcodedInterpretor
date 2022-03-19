package master.model.statement.CountDownLatch;

import master.collection.dictionary.MyIDictionary;
import master.model.ProgramState;
import master.model.expression.Expression;
import master.model.statement.IStatement;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements IStatement {
    private String var;
    private Expression expression;

    private static Lock lock = new ReentrantLock();

    public NewLatchStatement(String var, Expression expression){
        this.var = var;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeapTable();
        MyIDictionary<Integer, Integer> latchTable = state.getLatchTable();

        Integer latchAddress = state.getNewLatchAddress();
        latchTable.put(latchAddress, expression.evaluate(symbolTable, heapTable));
        symbolTable.put(var, latchAddress);
        state.setSymbolTable(symbolTable);
        state.setHeapTable(heapTable);
        lock.unlock();
        return null;
    }

    @Override
    public String toString(){
        return "newLatch( " + var + ", " + expression.toString() + " )";
    }
}
