package exceptions;

public class AlreadyFoundElementException extends Exception {
    public AlreadyFoundElementException(String itemType) {
        super("This " + itemType + " is already found!");
    }
}
