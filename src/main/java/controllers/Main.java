package controllers;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Create a Logger
        Logger logger
                = Logger.getLogger(
                Main.class.getName());

        // Call info method
        logger.info("Hello, World!");
    }
}
