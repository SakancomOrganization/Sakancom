package launchers;

import controllers.Login;
import enums.UserType;
import printers.MenusPrinter;
import scanners.CustomizedScanners;
import views.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MenusHandler {
    private static final Logger logger = Logger.getLogger(MenusHandler.class.getName());
    private static final String INVALID_CHOICE = "Invalid Choice :( !";
    private static final String CHOICE = "choice";

    private MenusHandler() {

    }

    private static void determineUserMenu() throws FileNotFoundException {
        try {
            UserType currentUserType = Login.getCurrentUserType();
            if(currentUserType == UserType.ADMIN) {
                adminMenuHandler();
            } else if(currentUserType == UserType.OWNER) {
                ownerMenuHandler();
            } else if(currentUserType == UserType.TENANT) {
                tenantMenuHandler();
            }
        } catch (NullPointerException e) {
            // prevent null pointer exception
        }

    }
    private static void mainMenuOptions(int choice) throws IOException {
        if(choice == 1) {
            LoginView.loginView();
            determineUserMenu();
        } else if (choice == 2) {
            LoginView.forgetPasswordView();
        } else if (choice == 3) {
            LoginView.updateEmailView();
        } else if(choice == 4) {
            SignUpView.signUpView();
        } else if(choice == 5) {
            MenusPrinter.printFinishSessionMsg();
        } else {
            logger.warning(INVALID_CHOICE);
        }
    }

    public static void mainMenuHandler() throws IOException {
        MenusPrinter.printWelcomeSessionMsg();
        while (true) {
            MenusPrinter.printMainMenu();
            int choice = CustomizedScanners.scanInt(CHOICE, new Scanner(System.in));
            mainMenuOptions(choice);
            if(choice == 5)
                break;
        }
    }

    private static void adminOptions(int choice) {
        if(choice == 1) {
            AdminView.listAllUsersView();
        } else if (choice == 2) {
            AdminView.listAllBuildingsView();
        } else if (choice == 3) {
            AdminView.listAllBuildingsInHouseView();
        } else if(choice == 4) {
            AdminView.searchAboutUsersView();
        } else if(choice == 5) {
            AdminView.searchAboutBuildingsView();
        } else if(choice == 6) {
            AdminView.removeUserView();
        } else if(choice == 7) {
            AdminView.getAllUpdatedBuildingsView();
        } else if(choice == 8) {
            AdminView.acceptBuildingUpdatesView();
        } else if(choice == 9) {
            AdminView.rejectBuildingUpdatesView();
        } else if(choice == 10) {
            AdminView.getAllUpdatesHousesView();
        } else if(choice == 11) {
            AdminView.acceptHousesUpdatesView();
        } else if(choice == 12) {
            AdminView.rejectHousesUpdatesView();
        } else if(choice == 13) {
            AdminView.getStatisticsView();
        } else if(choice == 14) {
            LogoutView.logoutView();
        } else {
            logger.warning(INVALID_CHOICE);
        }
    }

    public static void adminMenuHandler() {
        while (true) {
            MenusPrinter.printAdminMenu();
            int choice = CustomizedScanners.scanInt(CHOICE, new Scanner(System.in));
            adminOptions(choice);
            if(choice == 14)
                break;
        }
    }

    private static void ownerOptions(int choice) throws FileNotFoundException {
        if(choice == 1) {
            OwnerView.listAllOwnBuildingsView();
        } else if (choice == 2) {
            OwnerView.listAllHousesInOwnBuildingView();
        } else if (choice == 3) {
            OwnerView.listAllRejectBuildingsView();
        } else if(choice == 4) {
            OwnerView.listAllHousesInOwnBuildingView();
        } else if(choice == 5) {
            OwnerView.updateBuildingInfoView();
        } else if(choice == 6) {
            OwnerView.updateHouseInfoView();
        } else if(choice == 7) {
            OwnerView.addBuildingView();
        } else if(choice == 8) {
            OwnerView.addHouseView();
        } else if(choice == 9) {
            OwnerView.addImageView();
        } else if(choice == 10) {
            OwnerView.getAllSaleRequestsView();
        } else if(choice == 11) {
            OwnerView.acceptSaleRequestView();
        } else if(choice == 12) {
            OwnerView.breakSaleStatusView();
        } else if(choice == 13) {
            userGeneralMenuHandler();
        } else if(choice == 14) {
            LogoutView.logoutView();
        } else {
            logger.warning(INVALID_CHOICE);
        }
    }

    public static void ownerMenuHandler() throws FileNotFoundException {
        while (true) {
            MenusPrinter.printOwnerMenu();
            int choice = CustomizedScanners.scanInt(CHOICE, new Scanner(System.in));
            ownerOptions(choice);
            if(choice == 14)
                break;
        }
    }

    private static void tenantOptions(int choice) {
        if(choice == 1) {
            TenantView.listAvailableHousesView();
        } else if(choice == 2) {
            TenantView.listTenantHouses();
        } else if(choice == 3) {
            TenantView.requestHouseView();
        } else if(choice == 4) {
            TenantView.leaveHouseView();
        } else if(choice == 5) {
            TenantView.getAllNeighborsView();
        } else if(choice == 6) {
            TenantView.seeImagesView();
        } else if(choice == 7) {
            userGeneralMenuHandler();
        } else if(choice == 8) {
            LogoutView.logoutView();
        } else {
            logger.warning(INVALID_CHOICE);
        }
    }

    public static void tenantMenuHandler() {
        while (true) {
            MenusPrinter.printTenantMenu();
            int choice = CustomizedScanners.scanInt(CHOICE, new Scanner(System.in));
            tenantOptions(choice);
            if(choice == 8)
                break;
        }
    }

    public static void userGeneralActions(int choice) {
        if(choice == 1) {
            UserGeneralOperationsView.rateHouseView();
        } else if(choice == 2) {
            UserGeneralOperationsView.updateInfoView();
        } else if(choice == 3) {
            UserGeneralOperationsView.searchAboutHousesView();
        } else if(choice == 4) {
            logger.info("Navigate to role menu!");
        } else  {
            logger.warning(INVALID_CHOICE);
        }
    }

    public static void userGeneralMenuHandler() {
        while (true) {
            MenusPrinter.printUserGeneralOperationsMenu();
            int choice = CustomizedScanners.scanInt(CHOICE, new Scanner(System.in));
            userGeneralActions(choice);
            if(choice == 4)
                break;
        }
    }
}
