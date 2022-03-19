package master.model.statement;

import master.collection.stack.MyIStack;
import master.model.ProgramState;
import master.model.exceptions.ToyLanguageInterpreterException;
import master.model.expression.ArithmeticExpression;
import master.model.expression.Expression;

public class ConditionalAssignStatement implements IStatement{

    private final String var;
    private final Expression condition;
    private final Expression thenBranch;
    private final Expression elseBranch;

    public ConditionalAssignStatement(String var, Expression condition, Expression thenBranch, Expression elseBranch) {
        this.var = var;
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, Exception {
        MyIStack<IStatement> executionStack = state.getExecutionStack();
        IStatement newStmt = new IfStatement(condition, new AssignStatement(var, thenBranch),
                                            new AssignStatement(var, elseBranch));
        executionStack.push(newStmt);
        state.setExecutionStack(executionStack);
        return null;
    }

    @Override
    public String toString()
    {
        return var + " = ( " + condition.toString() + " ) ? " + thenBranch.toString() + " : " + elseBranch.toString();
    }
}
