package master.model.statement.Lock;

import master.collection.dictionary.MyIDictionary;
import master.model.ProgramState;
import master.model.statement.IStatement;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UnlockStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public UnlockStatement(String var){
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
            throw new Exception("No such index in latchTable");
        if (lockValue.equals(state.getId_thread())) {
            lockTable.put(foundIndex, -1);
            state.setLockTable(lockTable);
        }
        lock.unlock();
        return null;
    }

    @Override
    public String toString(){
        return "unlock( " + var + " )";
    }
}
