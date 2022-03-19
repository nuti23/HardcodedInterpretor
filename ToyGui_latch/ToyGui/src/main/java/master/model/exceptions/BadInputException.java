package master.model.exceptions;

public class BadInputException extends ToyLanguageInterpreterException {
    public BadInputException(String message){
        super(message);
    }
}
