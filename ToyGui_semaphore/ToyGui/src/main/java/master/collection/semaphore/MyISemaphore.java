package master.collection.semaphore;

import javafx.util.Pair;
import master.collection.dictionary.MyIDictionary;

import java.util.List;

public interface MyISemaphore {
    void setSemaphore(MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphore);
    MyIDictionary<Integer, Pair<Integer, List<Integer>>> getSemaphore();
    Integer getSemaphorAddress();

    String toString();

    void put(Integer foundIndex, Pair<Integer, List<Integer>> integerListPair);
}
