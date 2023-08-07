package launchers;

import exceptions.AlreadyFoundElementException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import models.Sakancom;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, AlreadyFoundElementException, UnacceptableValueException, ParseException, InvalidEmailFormatException {
        /*
        This is the main
         */
        Sakancom.initSakancomWithData();
        MenusHandler.mainMenuHandler();
    }
}
