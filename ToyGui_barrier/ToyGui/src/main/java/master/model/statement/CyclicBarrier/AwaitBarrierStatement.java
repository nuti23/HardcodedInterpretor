package master.model.statement.CyclicBarrier;

import javafx.util.Pair;
import master.collection.dictionary.MyIDictionary;
import master.collection.stack.MyIStack;
import master.model.ProgramState;
import master.model.statement.IStatement;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitBarrierStatement implements IStatement {
    private String var;
    private static Lock lock = new ReentrantLock();

    public AwaitBarrierStatement(String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        lock.lock();
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        MyIDictionary<Integer, Pair<Integer, List<Integer>>> barrierTable = state.getBarrierTable();

        Integer foundIndex = state.getSymbolTable().get(var);

        if(foundIndex == null)
            throw new Exception("No such variable in symbolTable");
        Pair<Integer, List<Integer>> barrierValue = barrierTable.get(foundIndex);
        List<Integer> threads = barrierValue.getValue();
        Integer n1 = barrierValue.getKey();
        Integer nl = threads.size();

        if (n1 > nl)
        {
            if(barrierValue.getValue().contains(state.getId_thread()))
                executionStack.push(this);
            else
            {
                threads.add(state.getId_thread());
                state.getBarrierTable().put(foundIndex, new Pair<>(n1, threads));
            }
        }
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "awaitBarrier( " + var + " )";
    }
}
