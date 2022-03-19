package master.model.statement.Semaphore;


import javafx.util.Pair;
import master.collection.dictionary.MyIDictionary;
import master.model.ProgramState;
import master.model.exceptions.ExpressionException;
import master.model.exceptions.ToyLanguageInterpreterException;
import master.model.expression.Expression;
import master.model.statement.IStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewSemaphoreStatement implements IStatement {

    public final String var;
    public final Expression expr;
    private static Lock lock = new ReentrantLock();

    public NewSemaphoreStatement(String var, Expression expr) {
        this.var = var;
        this.expr = expr;
    }


    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, Exception {
        lock.lock();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphoreTable = state.getSemaphoreTable().getSemaphore();

        Integer value = expr.evaluate(symbolTable, state.getHeap());
        Integer location = state.getSemaphoreTable().getSemaphorAddress();
        semaphoreTable.put(location, new Pair<>(value, new ArrayList<>()));
        symbolTable.put(var, location);

        state.setSemaphoreTable(semaphoreTable);
        state.setSymbolTable(symbolTable);
        lock.unlock();
        return null;
    }

    @Override
    public String toString()
    {
        return "newSemaphore( " + var + ", " + expr.toString() + ") ";
    }
}
