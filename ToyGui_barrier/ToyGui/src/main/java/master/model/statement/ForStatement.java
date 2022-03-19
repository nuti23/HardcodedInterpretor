package master.model.statement;

import master.collection.stack.MyIStack;
import master.model.ProgramState;
import master.model.exceptions.ToyLanguageInterpreterException;
import master.model.expression.BooleanExpression;
import master.model.expression.Expression;
import master.model.expression.VariableExpression;

public class ForStatement implements IStatement{

    private String var;
    private final Expression initialization;
    private final Expression condition;
    private final Expression step;
    private IStatement statement;

    public ForStatement(String v,Expression init, Expression cond, Expression st, IStatement stmt) {
        this.var = v;
        this.initialization = init;
        this.condition = cond;
        this.step = st;
        this.statement = stmt;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, Exception {
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        IStatement newStatement = new CompoundStatement(new AssignStatement(var, initialization),
                                new WhileStatement(new BooleanExpression(new VariableExpression(var),
                                        condition, "<"), new CompoundStatement(statement, new AssignStatement(var, step))));
        executionStack.push(newStatement);
        state.setExecutionStack(executionStack);

        return null;
    }

    @Override
    public String toString()
    {
        return "for( " + var + "=" + initialization.toString() + "; " + var + "<" + condition.toString() + "; " + var + "=" + step.toString() + " ) \n " + statement.toString();
    }
}
