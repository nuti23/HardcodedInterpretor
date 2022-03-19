package master.model.statement.Lock;

import master.collection.dictionary.MyIDictionary;
import master.collection.stack.MyIStack;
import master.model.ProgramState;
import master.model.statement.IStatement;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public LockStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        Integer foundIndex = state.getSymbolTable().get(var);

        if(foundIndex == null)
            throw new Exception("No such variable in symbolTable");
        MyIDictionary<Integer, Integer> lockTable = state.getLockTable();
        Integer lockValue = lockTable.get(foundIndex);
        if (lockValue == null)
            throw new Exception("No such index in lockTable");
        if (lockValue == -1) {
            lockTable.put(foundIndex, state.getId_thread());
            state.setLockTable(lockTable);
        }
        else{
            MyIStack<IStatement> executionStack = state.getExecutionStack();
            executionStack.push(this);
            state.setExecutionStack(executionStack);
        }

        lock.unlock();
        return null;
    }

    @Override
    public String toString(){
        return "lock( " + var + " )";
    }
}
