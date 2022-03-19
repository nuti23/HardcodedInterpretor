package master.model.util;

import master.collection.stack.MyIStack;
import master.collection.stack.MyStack;

public class AddressBuilder {

    private Integer address = 1;
    private static MyIStack<Integer> freeAddress = new MyStack<>();

    public Integer getFreeAddress() {
        return freeAddress.isEmpty() ? this.address++ : freeAddress.pop();
    }
}
