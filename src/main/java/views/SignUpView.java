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
import java.util.Scanner;
import java.util.logging.Logger;

public class SignUpView {
    private static final Logger logger = Logger.getLogger(SignUpView.class.getName());
    private SignUpView() {

    }

    public static void signUpView() {
        String username = CustomizedScanners.scanNonEmptyString("username", new Scanner(System.in));
        String password = CustomizedScanners.scanNonEmptyString("password", new Scanner(System.in));
        UserType userType = CustomizedScanners.scanUserType(new Scanner(System.in));
        // name
        String firstName = CustomizedScanners.scanNonEmptyString("first name", new Scanner(System.in));
        String middleName = CustomizedScanners.scanNonEmptyString("middle name", new Scanner(System.in));
        String lastName = CustomizedScanners.scanNonEmptyString("last name", new Scanner(System.in));
        // contact info
        String email = CustomizedScanners.scanNonEmptyString("email", new Scanner(System.in));
        String phoneNumber = CustomizedScanners.scanNonEmptyString("phone number", new Scanner(System.in));
        Date birthdate = CustomizedScanners.scanBirthdate(new Scanner(System.in));
        String major = CustomizedScanners.scanNonEmptyString("major", new Scanner(System.in));
        // user location
        String city = CustomizedScanners.scanNonEmptyString("city", new Scanner(System.in));
        String street = CustomizedScanners.scanNonEmptyString("street", new Scanner(System.in));
        String building = CustomizedScanners.scanNonEmptyString("building", new Scanner(System.in));
        int floorNum = CustomizedScanners.scanInt("floor number", new Scanner(System.in));

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
                floorNum = CustomizedScanners.scanInt("floor number", new Scanner(System.in));
            } catch (AlreadyFoundElementException e) {
                logger.warning("Username is already used!");
                username = CustomizedScanners.scanNonEmptyString("username", new Scanner(System.in));
            } catch (WeakPasswordException e) {
                logger.warning("Weak password!");
                password = CustomizedScanners.scanNonEmptyString("password", new Scanner(System.in));
            } catch (InvalidEmailFormatException e) {
                logger.warning("Invalid email format!");
                email = CustomizedScanners.scanNonEmptyString("email", new Scanner(System.in));
            }
        }
    }
}
