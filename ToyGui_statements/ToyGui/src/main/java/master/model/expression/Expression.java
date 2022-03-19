package master.model.expression;


import master.collection.dictionary.MyIDictionary;
import master.model.exceptions.ADTEmptyException;
import master.model.exceptions.ComparisonExpression;
import master.model.exceptions.DivisionByZero;
import master.model.exceptions.VariableNotFoundException;

public abstract class Expression {
    abstract public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws ADTEmptyException, DivisionByZero, VariableNotFoundException, ComparisonExpression, Exception;

    abstract public String toString();
}
