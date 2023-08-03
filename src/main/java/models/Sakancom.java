package models;

import enums.HouseClassificationByGender;
import enums.UserType;
import exceptions.AlreadyFoundElementException;
import exceptions.BuildingNotFoundException;
import exceptions.UnacceptableValueException;
import exceptions.UserNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Sakancom {
    private static final List<User> users = new ArrayList<>();
    private static final List<Building> buildings = new ArrayList<>();
    private static User currentUser = null;
    private static int autoIncrementBuildingId = 1;

    // private constructor to hide the public one
    private Sakancom() {

    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Sakancom.currentUser = currentUser;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static List<Building> getBuildings() {
        return buildings;
    }

    public static void addUser(User user) throws AlreadyFoundElementException {
        if(users.contains(user))
            throw new AlreadyFoundElementException("user");
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void addBuilding(Building building) throws AlreadyFoundElementException {
        if(buildings.contains(building))
            throw new AlreadyFoundElementException("building");

        building.setId(autoIncrementBuildingId);
        buildings.add(building);
        incrementAutoIncrementBuildingId(); // increment id for the next addition
    }

    public static void removeBuilding(Building building) {
        buildings.remove(building);
    }

    public static Building getBuildingById(int buildingId) throws BuildingNotFoundException {
        List<Building> resultedBuildings = buildings.stream().filter(building -> buildingId == building.getId()).toList();
        if(resultedBuildings.isEmpty())
            throw new BuildingNotFoundException();

        return resultedBuildings.get(0);
    }

    public static User getUserByUsername(String username) throws UserNotFoundException {
        List<User> resultedUsers = users.stream().filter(user -> user.getUsername().equals(username)).toList();
        if(resultedUsers.isEmpty())
            throw new UserNotFoundException();
        return resultedUsers.get(0);
    }

    private static void incrementAutoIncrementBuildingId() {
        autoIncrementBuildingId++;
    }

    public static void initSakancomWithData() throws ParseException, UnacceptableValueException, AlreadyFoundElementException {
        // remove the previous objects
        clearSakancomData();

        // literals
        final String PERSONAL_BUILDING = "Personal Building";
        final String DATA_PATTERN = "dd/MM/yyyy";
        final String NABLUS = "Nablus";
        final String COMPUTER_ENGINEERING = "Computer Engineering";

        // add the first admin (Mohammad Alawneh)
        User firstAdmin = new User("mo-alawneh",
                "Mohammad62002",
                UserType.ADMIN,
                new Name("Mohammad","AbdAllateef","Alawneh"),
                new UserLocation("Jenin","Abu-Baker",PERSONAL_BUILDING,2),
                new ContactInfo("mo.a.alawneh@gmail.com","0592838433",
                        new SimpleDateFormat(DATA_PATTERN).parse("12/06/2002"),COMPUTER_ENGINEERING));

        // add the second admin (Najat Mansour)
        User secondAdmin = new User("najat-mansour",
                "Najat12003",
                UserType.ADMIN,
                new Name("Najat","Sameer","Mansour"),
                new UserLocation(NABLUS,"AlEtihad",PERSONAL_BUILDING,1),
                new ContactInfo("s12028099@stu.najah.edu","0598892461",
                        new SimpleDateFormat(DATA_PATTERN).parse("28/01/2003"),COMPUTER_ENGINEERING));

        // add an owner
        User owner = new User("haya-sam",
                "HaySam",
                UserType.OWNER,
                new Name("Haya","Yaser","Samaana"),
                new UserLocation(NABLUS,"Tunis",PERSONAL_BUILDING,1),
                new ContactInfo("mohammad1jaba2002@gmail.com","0599112233",
                        new SimpleDateFormat(DATA_PATTERN).parse("25/05/1984"),COMPUTER_ENGINEERING));

        // add a tenant
        User tenant = new User("than@mare",
                "tHaNaMaRee",
                UserType.TENANT,
                new Name("Thana","Mahmoud","Mari"),
                new UserLocation(NABLUS,"JamalAbdAlNasser",PERSONAL_BUILDING,1),
                new ContactInfo("s12028067@stu.najah.edu","0599332211",
                        new SimpleDateFormat(DATA_PATTERN).parse("25/05/1992"),COMPUTER_ENGINEERING));

        // add a building
        Building building = new Building(1,
                "Golden House",
                owner,
                new Location("Nablus","Rafidia"));

        // add a house
        House house = new House(1,
                new Services(true, true, true, true, true, 3, 2),
                2000,
                1,
                HouseClassificationByGender.FAMILY);

        addUser(firstAdmin);
        addUser(secondAdmin);
        addUser(owner);
        addUser(tenant);
        addBuilding(building);
        building.addHouse(house);
    }

    private static void clearSakancomData() {
        // reset the autoIncrementBuildingId, then add
        autoIncrementBuildingId = 1;
        // reset the current user
        Sakancom.setCurrentUser(null);
        // remove all the users
        Sakancom.getUsers().clear();
        // remove all the buildings
        Sakancom.getBuildings().clear();
    }
}
