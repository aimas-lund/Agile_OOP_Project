package management;

import exceptions.FormatException;

import java.util.Date;

public abstract class Person {
    // TODO: Consider creating a factory design around person, or staff and patients separately

    private String name;
    private String surname;
    private Date birthdate;
    private int gender;
    private String homeAddress;
    private int phoneNumber;
    private String uniqueId;

    Person() {

    }

    Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getGender() {
        return gender;
    }

    void setGender(int gender) {
        this.gender = gender;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    void setPhoneNumber(int phoneNumber) throws FormatException {
        // Danish phone numbers for now
        if (String.valueOf(phoneNumber).length() == 8) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new FormatException("Wrong phone number");
        }
    }

    public String getUniqueId() {
        return uniqueId;
    }

    void setUniqueId(String uniqueID) {
        this.uniqueId = uniqueID;
    }

    public abstract String[] getPersonInformation();

}