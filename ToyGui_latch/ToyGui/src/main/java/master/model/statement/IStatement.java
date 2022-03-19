package master.model.statement;

import master.model.ProgramState;
import master.model.exceptions.ToyLanguageInterpreterException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, Exception;
    String toString();
}
