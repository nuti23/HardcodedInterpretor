package master.model.statement;

import master.collection.stack.MyIStack;
import master.model.ProgramState;
import master.model.exceptions.ToyLanguageInterpreterException;
import master.model.expression.Expression;

public class IfStatement implements IStatement{
    private final Expression expression;
    private final IStatement thenStatement;
    private final IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        int value = 0;
        try{
            value = expression.evaluate(state.getSymbolTable(), state.getHeap());
        }
        catch (Exception e){
            throw new ToyLanguageInterpreterException(e.getMessage());
        }
        if(value != 0) stack.push(thenStatement);
        else stack.push(elseStatement);
        return null;
    }

    @Override
    public String toString(){
        return "if(" + expression.toString() + ") then " + thenStatement.toString() + " else " + elseStatement.toString();
    }
}
