package core.persons;

import core.buildings.Event;
import core.buildings.Observable;
import core.buildings.Observer;
import exceptions.FormatException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public abstract class Person implements Observable {
    private String uniqueId;
    private String name;
    private String surname;
    private Date birthdate;
    private Gender gender;
    private String homeAddress;
    private int phoneNumber;
    private ArrayList<Observer> listeners;

    Person() {
        listeners.add(new PersonObserver());
    }

    public Person(String uniqueId) {
        this();
        this.uniqueId = uniqueId;
    }

    protected Person(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
        this(name, surname, birthdate, gender, homeaddress, phonenumber);
        this.setUniqueId(uniqueId);
    }

    protected Person(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
        this();
        this.setName(name);
        this.setSurname(surname);
        this.setBirthdate(birthdate);
        this.setGender(gender);
        this.setHomeAddress(homeaddress);
        try {
            this.setPhoneNumber(phonenumber);
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    Person(String name, String surname) {
        this();
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

    public Gender getGender() {
        return gender;
    }

    void setGender(Gender gender) {
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

    public String dateToString(Date birthdate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return birthdate == null ? "N/A" : format.format(birthdate);
    }

    public boolean equals(Person person) {
        return uniqueId.equals(person.uniqueId);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Person) {
            return equals((Person) obj);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (uniqueId == null) {
            return super.hashCode();
        }
        return uniqueId.hashCode();
    }

    @Override
    public void addChangeListener(Observer newListener) {
        listeners.add(newListener);
    }

    @Override
    public void removeChangeListener(Observer listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(Object source, Event action, Object oldValue, Object newValue) {
        for (Observer listener
                : listeners) {
            listener.objectChanged(this, action, oldValue, newValue);
        }
    }
}