package printers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MenusPrinter {
    private static final Logger logger = Logger.getLogger(MenusPrinter.class.getName());
    public static final String FORMAT = "%n|%-5s| %-30s|";
    public static final String LOGOUT = "logout";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private MenusPrinter() {

    }

    public static void printWelcomeSessionMsg() {
        String outputString = ANSI_YELLOW + "\nHello and Welcome to Sakancom Application!\n" +
                "This Application is power by Najat and Mohammad!\n\n";
        logger.info(outputString);
    }

    public static void printFinishSessionMsg() {
        String outputString = ANSI_YELLOW + "\nThanks to use this application\n" +
                "Please, communicate us for any help!";
        logger.info(outputString);
    }

    private static void printMenu(List<String> actions) {
        StringBuilder outputString = new StringBuilder();
        outputString.append(ANSI_YELLOW);
        CollectionsPrinter.appendHorizontalLine(outputString, 39);
        for(int i = 0; i < actions.size(); i++) {
            outputString.append(String.format(FORMAT, i + 1, actions.get(i)));
        }
        CollectionsPrinter.appendHorizontalLine(outputString, 39);
        String result = String.valueOf(outputString);
        logger.info(result);
    }

    public static void printMainMenu() {
        List<String> mainMenu = new ArrayList<>();
        mainMenu.add("login");
        mainMenu.add("forget the password");
        mainMenu.add("update the email");
        mainMenu.add("sign up");
        mainMenu.add("quit");

        printMenu(mainMenu);
    }

    public static void printAdminMenu() {
        List<String> adminActions = new ArrayList<>();
        adminActions.add("list all users");
        adminActions.add("list all buildings");
        adminActions.add("list all buildings in a house");
        adminActions.add("search about users");
        adminActions.add("search about buildings");
        adminActions.add("remove user");
        adminActions.add("get all updated buildings");
        adminActions.add("accept building updates");
        adminActions.add("reject building updates");
        adminActions.add("get all updated houses");
        adminActions.add("accept house updates");
        adminActions.add("reject house updates");
        adminActions.add("get statistics");
        adminActions.add(LOGOUT);

        printMenu(adminActions);
    }

    public static void printOwnerMenu() {
        List<String> ownerActions = new ArrayList<>();
        ownerActions.add("list all own buildings");
        ownerActions.add("list all houses in a building");
        ownerActions.add("list all rejected buildings");
        ownerActions.add("list all rejected houses");
        ownerActions.add("update building info");
        ownerActions.add("update house info");
        ownerActions.add("add a new building");
        ownerActions.add("add a new house");
        ownerActions.add("add a new image to a house");
        ownerActions.add("accept sale request");
        ownerActions.add("break sale request");
        ownerActions.add(LOGOUT);

        printMenu(ownerActions);
    }

    public static void printTenantMenu() {
        List<String> tenantActions = new ArrayList<>();
        tenantActions.add("list all available houses");
        tenantActions.add("list all own houses");
        tenantActions.add("request a new house");
        tenantActions.add("leave a house");
        tenantActions.add("get all neighbors for a house");
        tenantActions.add(LOGOUT);

        printMenu(tenantActions);
    }
}
