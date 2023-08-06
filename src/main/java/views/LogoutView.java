package views;

import controllers.Logout;

import java.util.logging.Logger;

public class LogoutView {
    private static final Logger logger = Logger.getLogger(LogoutView.class.getName());
    private LogoutView() {

    }

    public static void logoutView() {
        Logout.logout();
        logger.info("See you again :( :( :( !");
    }
}
