package exceptions;

public class BuildingNotFoundException extends Exception{
    public BuildingNotFoundException() {
        super("Invalid Building ID!");
    }
}
