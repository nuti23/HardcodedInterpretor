package master.model.statement;

import master.collection.stack.MyIStack;
import master.model.ProgramState;
import master.model.exceptions.ToyLanguageInterpreterException;

public class SleepStatement implements IStatement{

    private Integer number;

    public SleepStatement(Integer number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, Exception {
        if (number != 0)
        {
            MyIStack<IStatement> executionStack = state.getExecutionStack();
            executionStack.push(new SleepStatement(number - 1));
            state.setExecutionStack(executionStack);
        }
        return null;

    }

    @Override
    public String toString(){
        return "sleep( " + number.toString() + " )";
    }
}
