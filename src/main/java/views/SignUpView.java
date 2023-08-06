package views;

import controllers.SignUp;
import enums.UserType;
import exceptions.AlreadyFoundElementException;
import exceptions.InvalidEmailFormatException;
import exceptions.UnacceptableValueException;
import exceptions.WeakPasswordException;
import models.ContactInfo;
import models.Name;
import models.UserLocation;
import scanners.CustomizedScanners;

import java.util.Date;
import java.util.logging.Logger;

public class SignUpView {
    private static final Logger logger = Logger.getLogger(SignUpView.class.getName());
    private SignUpView() {

    }

    public static void signUpView() {
        String username = CustomizedScanners.scanNonEmptyString("username");
        String password = CustomizedScanners.scanNonEmptyString("password");
        UserType userType = CustomizedScanners.scanUserType();
        // name
        String firstName = CustomizedScanners.scanNonEmptyString("first name");
        String middleName = CustomizedScanners.scanNonEmptyString("middle name");
        String lastName = CustomizedScanners.scanNonEmptyString("last name");
        // contact info
        String email = CustomizedScanners.scanNonEmptyString("email");
        String phoneNumber = CustomizedScanners.scanNonEmptyString("phone number");
        Date birthdate = CustomizedScanners.scanBirthdate();
        String major = CustomizedScanners.scanNonEmptyString("major");
        // user location
        String city = CustomizedScanners.scanNonEmptyString("city");
        String street = CustomizedScanners.scanNonEmptyString("street");
        String building = CustomizedScanners.scanNonEmptyString("building");
        int floorNum = CustomizedScanners.scanInt("floor number");

        while (true) {
            try {
                Name name = new Name(firstName, middleName, lastName);
                UserLocation userLocation = new UserLocation(city, street, building, floorNum);
                ContactInfo contactInfo = new ContactInfo(email, phoneNumber, birthdate, major);
                SignUp.signUp(username, password, userType, name, userLocation, contactInfo);
                logger.info("Sign up successfully!");
                break;
            } catch (UnacceptableValueException e) {
                logger.warning("Floor number cannot be negative");
                floorNum = CustomizedScanners.scanInt("floor number");
            } catch (AlreadyFoundElementException e) {
                logger.warning("Username is already used!");
                username = CustomizedScanners.scanNonEmptyString("username");
            } catch (WeakPasswordException e) {
                logger.warning("Weak password!");
                password = CustomizedScanners.scanNonEmptyString("password");
            } catch (InvalidEmailFormatException e) {
                logger.warning("Invalid email format!");
                email = CustomizedScanners.scanNonEmptyString("email");
            }
        }
    }
}
