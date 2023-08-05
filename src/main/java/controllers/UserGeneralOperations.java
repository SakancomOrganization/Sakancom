package controllers;

import enums.HouseClassificationByGender;
import exceptions.*;
import helpers.PasswordChecker;
import helpers.StringsComparator;
import models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGeneralOperations {
    private UserGeneralOperations() {

    }

    public static void updateInfo(String field, String value) throws NumberFormatException, ParseException, NullPointerException, UnacceptableValueException, WeakPasswordException, InvalidEmailFormatException {
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

    private static List<House> searchInsideBuilding(int buildingId, Services services, int monthlyRent, Name ownerName, String buildingName, Location location, HouseClassificationByGender houseClassificationByGender) throws BuildingNotFoundException {
        Building building = Sakancom.getBuildingById(buildingId);
        List<House> subList = new ArrayList<>();
        for(House house : building.getHouses()) {
            if((services == null || house.getServices().equals(services))
                    && (monthlyRent == -1 || house.getMonthlyRent() <= monthlyRent)
                    && (ownerName == null || building.getOwner().getName().equals(ownerName))
                    && StringsComparator.compare(building.getName(), buildingName)
                    && (location == null || building.getLocation().equals(location))
                    && (houseClassificationByGender == null || house.getHouseClassificationByGender().equals(houseClassificationByGender))) {
                subList.add(house);
            }
        }
        return subList;
    }

    public static Map<Integer, List<House>> searchAboutHouses(Services services, int monthlyRent, Name ownerName, String buildingName, Location location, HouseClassificationByGender houseClassificationByGender) throws BuildingNotFoundException {
        Map<Integer, List<House>> resultHouses = new HashMap<>();
        for(Building building : Sakancom.getBuildings()) {
            List<House> subList = searchInsideBuilding(building.getId(), services, monthlyRent, ownerName, buildingName, location, houseClassificationByGender);
            if(!subList.isEmpty())
                resultHouses.put(building.getId(), subList);
        }
        return resultHouses;
    }
}
