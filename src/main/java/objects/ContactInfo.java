package objects;

import java.util.Date;

public class ContactInfo {
    private String email;
    private String phoneNumber;
    private Date birthdate;
    private String major;

    public ContactInfo(String email, String phoneNumber, Date birthdate, String major) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
