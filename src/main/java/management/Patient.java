package management;

import exceptions.FormatException;

import java.util.Date;

public class Patient extends Person {
    private boolean alive = true;

    public Patient() {
    }

    public Patient(String name, String surname) {
        super(name, surname);
    }

    public Patient(String uniqueId, String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber) {
        this(name, surname, birthdate, gender, homeaddress, phonenumber);
        this.setUniqueId(uniqueId);
    }

    public Patient(String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber) {
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

    @Override
    public String[] getPersonInformation() {
        return new String[]{this.getUniqueId(), this.getName(), this.getSurname(),
                dateToString(this.getBirthdate()), String.format("%d", this.getGender()), this.getHomeAddress(),
                String.format("%d", this.getPhoneNumber())};
    }

    public boolean isAlive() {
        return alive;
    }

}
