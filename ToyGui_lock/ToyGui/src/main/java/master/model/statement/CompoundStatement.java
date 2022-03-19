package master.model.statement;


import master.collection.stack.MyIStack;
import master.model.exceptions.ToyLanguageInterpreterException;
import master.model.ProgramState;

public class CompoundStatement implements IStatement {
    private final IStatement first;
    private final IStatement second;

    public CompoundStatement(IStatement first, IStatement second){
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public String toString(){
        return first.toString() + "; " + second.toString();
    }
}
