package master.model.util;

import master.collection.stack.MyIStack;
import master.collection.stack.MyStack;
import master.model.statement.IStatement;

public class AddressBuilder {
    private Integer address = 1;
    private static MyIStack<Integer> freeAddress = new MyStack<>();

    public Integer getFreeAddress() {
        if (freeAddress.isEmpty())
            return this.address++;
        return freeAddress.pop();
    }

}
