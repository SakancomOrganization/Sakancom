package views.scanners;

import java.io.Console;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

public class CustomizedScanner {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(CustomizedScanner.class.getName());
    private CustomizedScanner() {

    }

    private static void printScanMsg(String type) {
         String scanMsg = "Enter " + type + " :";
         logger.info(scanMsg);
    }

    private static void printWarnMsg(String type) {
        String warnMsg = "invalid " + type + "! Please, enter valid one";
        logger.warning(warnMsg);
    }

    public static int scanInt(String type) {
        printScanMsg(type);
        int scannedInt;
        while (true) {
            try {
                scannedInt = scanner.nextInt();
                break;
            } catch (InputMismatchException inputMismatchException) {
                scanner.next();
                printWarnMsg(type);
            }
        }
        return scannedInt;
    }

    public static String scanString(String type) {
        printScanMsg(type);
        String scanString = "";
        while(scanString.isEmpty()) {
            scanString = scanner.nextLine();
            if(scanString.isEmpty())
                printWarnMsg(type);
        }
        return scanString;
    }

    public static boolean scanBoolean(String type) {
        printScanMsg(type);
        boolean scanBoolean;
        while(true) {
            try {
                scanBoolean = scanner.nextBoolean();
                break;
            } catch (InputMismatchException inputMismatchException) {
                scanner.next();
                printWarnMsg(type);
            }
        }
        return  scanBoolean;
    }

}
