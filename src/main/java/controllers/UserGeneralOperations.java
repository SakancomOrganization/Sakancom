package controllers;

import enums.HouseClassificationByGender;
import exceptions.*;
import helpers.PasswordChecker;
import helpers.StringsComparator;
import models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public static List<House> searchAboutHouses(Services services, int monthlyRent, Name ownerName, String buildingName, Location location, HouseClassificationByGender houseClassificationByGender) {
        List<House> resultHouses = new ArrayList<>();
        for(Building building : Sakancom.getBuildings()) {
            for(House house : building.getHouses()) {
                if((services == null || house.getServices().equals(services))
                        && (monthlyRent == -1 || house.getMonthlyRent() <= monthlyRent)
                        && (ownerName == null || building.getOwner().getName().equals(ownerName))
                        && StringsComparator.compare(building.getName(), buildingName)
                        && (location == null || building.getLocation().equals(location))
                        && (houseClassificationByGender == null || house.getHouseClassificationByGender().equals(houseClassificationByGender))) {
                    resultHouses.add(house);
                }
            }
        }
        return resultHouses;
    }
}
