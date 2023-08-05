package views;

import controllers.SignUp;
import enums.UserType;
import exceptions.AlreadyFoundElementException;
import exceptions.UnacceptableValueException;
import exceptions.WeakPasswordException;
import models.ContactInfo;
import models.Name;
import models.UserLocation;

import java.util.Date;
import java.util.logging.Logger;

public class SignUpView {
    private static final Logger logger = Logger.getLogger(SignUpView.class.getName());
    private SignUpView() {

    }

    public static void signUpView() {
        String username = CustomizedScanners.scanString("username");
        String password = CustomizedScanners.scanString("password");
        UserType userType = CustomizedScanners.scanUserType();
        // name
        String firstName = CustomizedScanners.scanString("first name");
        String middleName = CustomizedScanners.scanString("middle name");
        String lastName = CustomizedScanners.scanString("last name");
        // contact info
        String email = CustomizedScanners.scanString("email");
        String phoneNumber = CustomizedScanners.scanString("phone number");
        Date birthdate = CustomizedScanners.scanBirthdate();
        String major = CustomizedScanners.scanString("major");
        // user location
        String city = CustomizedScanners.scanString("city");
        String street = CustomizedScanners.scanString("street");
        String building = CustomizedScanners.scanString("building");
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
                username = CustomizedScanners.scanString("username");
            } catch (WeakPasswordException e) {
                logger.warning("Weak password!");
                password = CustomizedScanners.scanString("password");
            }
        }
    }
}
