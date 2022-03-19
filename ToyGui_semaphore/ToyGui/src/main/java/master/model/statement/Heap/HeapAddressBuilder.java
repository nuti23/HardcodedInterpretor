package master.model.statement.Heap;


import master.collection.stack.MyIStack;
import master.collection.stack.MyStack;

public class HeapAddressBuilder {
    private Integer address = 1;
    private static final MyIStack<Integer> freeAddress = new MyStack<>();

    public Integer getFreeAddress(){
        return freeAddress.isEmpty() ? this.address++ : freeAddress.pop();
    }
}
