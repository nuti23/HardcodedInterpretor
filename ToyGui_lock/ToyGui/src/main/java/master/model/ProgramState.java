package master.model;

import master.collection.dictionary.MyDictionary;
import master.collection.dictionary.MyIDictionary;
import master.collection.stack.MyIStack;
import master.collection.stack.MyStack;
import master.model.exceptions.ADTEmptyException;
import master.model.statement.File.MyFilePair;
import master.model.statement.Heap.HeapAddressBuilder;
import master.model.statement.IStatement;
import master.model.util.AddressBuilder;

import java.util.ArrayList;
import java.util.List;

public class ProgramState {
    private MyIStack<IStatement> executionStack;
    private MyIDictionary<String, Integer> symbolTable;
    private List<Integer> outputList;
    private MyIDictionary<Integer, MyFilePair> fileTable;
    private IStatement originalProgram;
    private MyIDictionary<Integer, Integer> heapTable;
    private Integer id_thread = 1;
    private final HeapAddressBuilder b = new HeapAddressBuilder();
    private Integer last_id = 1;
    private MyIDictionary<Integer, Integer> lockTable;
    private AddressBuilder lockAddress = new AddressBuilder();

    public ProgramState(MyStack<IStatement> programStateMyStack, MyIDictionary<String, Integer> symbolTable,
                        List<Integer> outputList, IStatement originalProgram, MyIDictionary<Integer, MyFilePair> fileTable,
                        MyIDictionary<Integer, Integer> heap, Integer id, MyIDictionary<Integer, Integer> lockTable) {
        executionStack = programStateMyStack;
        this.symbolTable = symbolTable;
        this.outputList = outputList;
        this.fileTable = fileTable;
        heapTable = heap;
        this.originalProgram = originalProgram;
        this.id_thread = id;
        this.lockTable = lockTable;

        executionStack.push(originalProgram);
    }

    public void setLast_id(Integer last_id) {
        this.last_id = last_id;
    }

    public Integer getLast_id() {
        return last_id;
    }

    public Integer getNewAddress() {
        return b.getFreeAddress();
    }

    public Integer getLockAddress() { return lockAddress.getFreeAddress(); }

    public ProgramState(IStatement originalProgram) {
        executionStack = new MyStack<>();
        symbolTable = new MyDictionary<>();
        outputList = new ArrayList<>();
        fileTable = new MyDictionary<>();
        heapTable = new MyDictionary<>();
        this.originalProgram = originalProgram;
        lockTable = new MyDictionary<>();
        executionStack.push(originalProgram);
    }

    public Integer getId_thread() {
        return id_thread;
    }

    public void setId_thread(Integer id_thread) {
        this.id_thread = id_thread;
    }

    public MyIStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(MyIStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public MyIDictionary<String, Integer> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(MyIDictionary<String, Integer> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public List<Integer> getOutputList() {
        return outputList;
    }

    public MyIDictionary<Integer, Integer> getLockTable() {
        return lockTable;
    }

    public void setLockTable(MyIDictionary<Integer, Integer> lockTable) {
        this.lockTable = lockTable;
    }

    public void setOutputList(List<Integer> outputList) {
        this.outputList = outputList;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public MyIDictionary<Integer, MyFilePair> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<Integer, MyFilePair> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIDictionary<Integer, Integer> getHeap() {
        return heapTable;
    }

    public void setHeap(MyIDictionary<Integer, Integer> heapTable) {
        this.heapTable = heapTable;
    }

    public void addOut(Integer number) {
        this.outputList.add(number);
    }

    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public ProgramState oneStep() throws Exception {
        if (executionStack.isEmpty())
            throw new ADTEmptyException("Stack empty");
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    @Override
    public String toString() {
        return "Thread number id: " + id_thread.toString() + "\n" +
                "------------------------------------------------------\n" +
                "*****OutputList*****\n" +
                outputList.toString() + "\n" +
                "*****SymbolTable*****\n" +
                symbolTable.toString() + "\n" +
                "*****ExecutionStack*****\n" +
                executionStack.toString() + "\n" +
                "*****FileTable*****\n" +
                fileTable.toString() + "\n" +
                "*****HeapTable*****\n" +
                heapTable.toString() + "\n" +
                "*****LockTable***** \n" +
                lockTable.toString() + "\n" +
                "------------------------------------------------------\n\n\n";
    }
}
