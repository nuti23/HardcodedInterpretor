package master.model.statement;

import master.collection.dictionary.MyIDictionary;
import master.collection.stack.MyIStack;
import master.collection.stack.MyStack;
import master.model.ProgramState;
import master.model.exceptions.ToyLanguageInterpreterException;
import master.model.expression.ArithmeticExpression;
import master.model.expression.Expression;
import master.model.expression.OperationEnum;

public class SwitchStatement implements IStatement{

    private final Expression condition;
    private final Expression case1;
    private final IStatement statement1;
    private final Expression case2;
    private final IStatement statement2;
    private final IStatement statement3;

    public SwitchStatement(Expression condition, Expression case1, IStatement statement1, Expression case2, IStatement statement2, IStatement statement3) {
        this.condition = condition;
        this.case1 = case1;
        this.statement1 = statement1;
        this.case2 = case2;
        this.statement2 = statement2;
        this.statement3 = statement3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyLanguageInterpreterException, Exception {

        MyIStack<IStatement> exeStack = state.getExecutionStack();
        MyIDictionary<String, Integer> symbolTable = state.getSymbolTable();
        MyIDictionary<Integer, Integer> heapTable = state.getHeap();

        Integer exp = condition.evaluate(symbolTable, heapTable);
        Integer exp1 = case1.evaluate(symbolTable, heapTable);
        Integer exp2 = case2.evaluate(symbolTable, heapTable);
        IStatement newStatement;
        if(exp.equals(exp1))
            newStatement = statement1;
        else if(exp.equals(exp2))
            newStatement = statement2;
        else
            newStatement = statement3;
        exeStack.push(newStatement);
        state.setExecutionStack(exeStack);
        return null;
    }

    @Override
    public String toString(){
        return "switch( " + condition.toString() + ") \n(case( " + case1.toString() + " ) " + statement1.toString() + ")\n(case( " + case2.toString() + " ) " + statement2.toString() + ")\n(default " + statement3.toString() + " )";
    }
}
