package master.model.statement.CyclicBarrier;

import javafx.util.Pair;
import master.collection.dictionary.MyIDictionary;
import master.model.ProgramState;
import master.model.expression.Expression;
import master.model.statement.IStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements IStatement {
    private String var;
    private Expression expression;
    private static Lock lock = new ReentrantLock();

    public NewBarrierStatement(String var, Expression expression){
        this.expression = expression;
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Pair<Integer, List<Integer>>> barrierTable = state.getBarrierTable();

        Integer value = expression.evaluate(symbolTable, state.getHeapTable());
        Integer location = state.getBarrierAddress();
        barrierTable.put(location, new Pair<>(value, new ArrayList<>()));
        symbolTable.put(var, location);

        state.setBarrierTable(barrierTable);
        state.setSymbolTable(symbolTable);
        lock.unlock();
        return null;
    }

    @Override
    public String toString(){
        return "newBarrier( " + var + ", " + expression.toString() + ")";
    }
}
