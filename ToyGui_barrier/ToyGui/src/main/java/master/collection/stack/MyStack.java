package master.collection.stack;

import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private final Stack<T> stack;

    public MyStack(){
        stack = new Stack<T>();
    }

    @Override
    public T pop(){
        return stack.pop();
    }

    @Override
    public void push(T v)
    {
        stack.push(v);
    }

    @Override
    public boolean isEmpty()
    {
        return stack.empty();
    }

    @Override
    public String toString(){
        return stack.toString();
    }

    @Override
    public List<T> getAll() {
        return stack;
    }
}

