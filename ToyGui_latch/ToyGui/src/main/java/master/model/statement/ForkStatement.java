package master.model.statement;


import master.collection.stack.MyStack;
import master.model.exceptions.ToyLanguageInterpreterException;
import master.model.ProgramState;

import java.io.FileNotFoundException;

public class ForkStatement implements IStatement {
    private final IStatement statement;

    public ForkStatement(IStatement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, FileNotFoundException {
        ProgramState newProgram = new ProgramState(new MyStack<>(), state.getSymbolTable().clone_dict(),
                state.getOutputList(), this.statement, state.getFileTable(), state.getHeap(), state.getLast_id() + 10,
                state.getLatchTable());
        state.setLast_id(state.getLast_id() + 10);
        return newProgram;
    }

    @Override
    public String toString() {
        return "fork(" + this.statement.toString() + ")";
    }
}
