package objects;

public class User {
    private String username;
    private String password;
    private Type type;
    private Name name;
    private Location location;
    private ContactInfo contactInfo;

    public User() {

    }

    public User(String username, String password, Type type, Name name, Location location, ContactInfo contactInfo) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.name = name;
        this.location = location;
        this.contactInfo = contactInfo;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
}
