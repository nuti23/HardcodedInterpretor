package master.model.statement;


import master.model.ProgramState;
import master.model.expression.Expression;
import master.model.expression.NotExpression;

public class RepeatStatement implements IStatement {
    private final IStatement statement;
    private final Expression expression;

    public RepeatStatement(IStatement statement, Expression expression)
    {
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Exception {
        IStatement act = new CompoundStatement(statement, new WhileStatement(new NotExpression(expression), statement));
        state.getExecutionStack().push(act);
        return null;
    }

    @Override
    public String toString(){
        return "( repeat (" + statement.toString() + ") until " + expression.toString() + " )";
    }
}