package master.collection.semaphore;

import javafx.util.Pair;
import master.collection.dictionary.MyDictionary;
import master.collection.dictionary.MyIDictionary;
import master.model.util.AddressBuilder;

import java.util.List;

public class MySemaphore implements MyISemaphore{
    MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphore;
    private AddressBuilder semaphoreAddress = new AddressBuilder();

    public MySemaphore(){
        semaphore = new MyDictionary<>();
    }

    public MyIDictionary<Integer, Pair<Integer, List<Integer>>> getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphore) {
        this.semaphore = semaphore;
    }

    public Integer getSemaphorAddress(){
        return semaphoreAddress.getFreeAddress();
    }

    @Override
    public void put(Integer foundIndex, Pair<Integer, List<Integer>> integerListPair) {
        semaphore.put(foundIndex, integerListPair);
    }

    @Override
    public String toString()
    {
        return semaphore.toString();
    }
}
