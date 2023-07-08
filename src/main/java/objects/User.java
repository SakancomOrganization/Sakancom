package objects;

import enums.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private UserType userType;
    private Name name;
    private Location location;
    private ContactInfo contactInfo;
    private List<Furniture> furnitureList;

    public User () {

    }

    public User(String username, String password, UserType userType, Name name, Location location, ContactInfo contactInfo) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.location = location;
        this.contactInfo = contactInfo;
        furnitureList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }

    public void addFurniture(Furniture furniture) {
        if(!furnitureList.contains(furniture)) {
            furnitureList.add(furniture);
        }
    }

    public void removeFurniture(Furniture furniture) {
        furnitureList.remove(furniture);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User user) {
            return this.username.equals(user.getUsername());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
