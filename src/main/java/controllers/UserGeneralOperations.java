package controllers;

import enums.HouseClassificationByGender;
import exceptions.BuildingNotFoundException;
import exceptions.HouseNotFoundException;
import exceptions.UnacceptableValueException;
import exceptions.WeakPasswordException;
import helpers.PasswordChecker;
import models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class UserGeneralOperations {
    private UserGeneralOperations() {

    }

    public static void updateInfo(String field, String value) throws NumberFormatException, ParseException, NullPointerException, UnacceptableValueException, WeakPasswordException {
        User user = Sakancom.getCurrentUser();
        if(field.equalsIgnoreCase("password")) {
            if(!PasswordChecker.isStrongPassword(value)) {
                throw new WeakPasswordException();
            }
            user.setPassword(value);
        }
        else if(field.equalsIgnoreCase("firstName")) {
            user.getName().setFirstName(value);
        } else if(field.equalsIgnoreCase("secondName")) {
            user.getName().setMiddleName(value);
        } else if(field.equalsIgnoreCase("lastName")) {
            user.getName().setLastName(value);
        } else if(field.equalsIgnoreCase("city")) {
            user.getUserLocation().setCity(value);
        } else if(field.equalsIgnoreCase("street")) {
            user.getUserLocation().setStreet(value);
        } else if(field.equalsIgnoreCase("building")) {
            user.getUserLocation().setBuilding(value);
        } else if(field.equalsIgnoreCase("floor")) {
            user.getUserLocation().setFloorNum(Integer.parseInt(value));
        } else if(field.equalsIgnoreCase("email")) {
            user.getContactInfo().setEmail(value);
        } else if(field.equalsIgnoreCase("phoneNumber")) {
            user.getContactInfo().setPhoneNumber(value);
        } else if(field.equalsIgnoreCase("birthDate")) {
            user.getContactInfo().setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(value));
        } else if(field.equalsIgnoreCase("major")) {
            user.getContactInfo().setMajor(value);
        }
    }

    public static void rateHouse(int buildingId, int houseId, int rate) throws HouseNotFoundException, UnacceptableValueException, BuildingNotFoundException {
        User currentUser = Sakancom.getCurrentUser();
        House house = Sakancom.getBuildingById(buildingId).getHouseById(houseId);
        house.getHouseRate().setUserRate(currentUser, rate);
    }

    public static List<House> searchAboutHouses(Services services, int monthlyRent, Location location, HouseClassificationByGender houseClassificationByGender) {
        return Sakancom.searchAboutHouses(services, monthlyRent, null, location, houseClassificationByGender);
    }
}
