package helpers;

public class EmailFormatChecker {
    private EmailFormatChecker() {

    }

    public static boolean hasCorrectEmailFormat(String email) {
        if(countCharOccurrences(email, '@') != 1)
            return false;

        if(countCharOccurrences(email, '.') < 1)
            return false;

        String [] emailSplitting = email.split("@");

        return emailSplitting[1].equals("gmail.com")
                || emailSplitting[1].equals("hotmail.com")
                || emailSplitting[1].equals("yahoo.com")
                || emailSplitting[1].equals("outlook.com")
                || emailSplitting[1].split("\\.")[emailSplitting[1].split("\\.").length - 1].equals("edu");
    }

    private static int countCharOccurrences(String email, char key) {
        int count = 0;
        for(char ch : email.toCharArray())
            if(ch == key)
                count++;
        return count;
    }
}
