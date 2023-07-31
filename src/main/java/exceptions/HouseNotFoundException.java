package exceptions;

public class HouseNotFoundException extends Exception {
    public HouseNotFoundException() {
        super("Invalid House ID!");
    }
}
