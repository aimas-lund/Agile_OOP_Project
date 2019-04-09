package management;

import java.util.Date;

public abstract class Person {
    private String name;
    private String surname;
    private Date birthdate;
    private int gender;
    private String homeAddress;
    private int phoneNumber;
    private int uniqueId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        if (String.valueOf(phoneNumber).length() == 8) {
            this.phoneNumber = phoneNumber;
        } else {
            // TODO: Add exception for invalid phone number
            // Stopped developing this, as it was not related to my scenario
            System.out.println("Invalid phone number");
        }
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueID) {
        this.uniqueId = uniqueID;
    }

}
