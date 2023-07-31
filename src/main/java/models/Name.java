package models;

import java.util.Objects;

public class Name {
    private String firstName;
    private String middleName;
    private String lastName;

    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Name name) {
            return (name.getFirstName().isEmpty() || firstName.equalsIgnoreCase(name.getFirstName()))
                    && (name.getMiddleName().isEmpty() || middleName.equalsIgnoreCase(name.getMiddleName()))
                    && (name.getLastName().isEmpty() || lastName.equalsIgnoreCase(name.getLastName()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName);
    }
}
