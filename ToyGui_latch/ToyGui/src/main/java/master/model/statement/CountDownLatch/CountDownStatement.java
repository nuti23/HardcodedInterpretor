package master.model.statement.CountDownLatch;

import master.collection.dictionary.MyIDictionary;
import master.collection.stack.MyIStack;
import master.model.ProgramState;
import master.model.expression.ConstantExpression;
import master.model.statement.IStatement;
import master.model.statement.PrintStatement;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public CountDownStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        MyIDictionary<Integer, Integer> latchTable = state.getLatchTable();

        Integer foundIndex = state.getSymbolTable().get(var);

        if(foundIndex == null)
            throw new Exception("No such variable in symbolTable");
        Integer latchValue = latchTable.get(foundIndex);
        if (latchValue == null)
            throw new Exception("No such index in latchTable");
        if (latchValue > 0) {
            latchTable.put(foundIndex, latchValue - 1);
            executionStack.push(new PrintStatement(new ConstantExpression(state.getLast_id())));
            state.setExecutionStack(executionStack);
            state.setLatchTable(latchTable);
        }
        lock.unlock();
        return null;
    }

    @Override
    public String toString(){
        return "countDown( " + var + " )";
    }
}
