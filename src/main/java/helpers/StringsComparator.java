package helpers;

public class StringsComparator {
    private StringsComparator() {

    }

    public static boolean compare(String base, String input) {
        return input.isEmpty()
                || base.toLowerCase().startsWith(input.toLowerCase())
                || base.toLowerCase().endsWith(input.toLowerCase())
                || base.toLowerCase().contains(input.toLowerCase());
    }
}
