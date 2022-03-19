package master.model.expression;


import master.collection.dictionary.MyIDictionary;

public class NotExpression extends Expression {
    private final Expression expression;

    public NotExpression(Expression expression){
        this.expression = expression;
    }


    @Override
    public Integer evaluate(MyIDictionary<String, Integer> symbolTable, MyIDictionary<Integer, Integer> heapTable) throws Exception {
        return expression.evaluate(symbolTable, heapTable) == 0 ? 1 : 0;
    }

    @Override
    public String toString() {
        return "!" + expression.toString();
    }
}