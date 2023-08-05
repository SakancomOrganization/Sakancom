package views;

public class ViewsValidation {
    private ViewsValidation() {

    }

    public static boolean isNonNegativeNumber(int roomsNum) {
        return roomsNum >= 0;
    }

    public static boolean isValidRate(int rate) {
        return rate >=0 && rate <= 5;
    }
}
