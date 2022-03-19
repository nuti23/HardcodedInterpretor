package master.model.statement;


import master.model.ProgramState;
import master.model.exceptions.ToyLanguageInterpreterException;
import master.model.expression.Expression;

public class PrintStatement implements IStatement{
    Expression expression;

    public PrintStatement(Expression expression){
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print( " + expression.toString() + " )";
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException {
        try{
            state.addOut(expression.evaluate(state.getSymbolTable(), state.getHeap()));
        }
        catch (Exception e){
            throw new ToyLanguageInterpreterException(e.getMessage());
        }
        return null;
    }
}
