package core.persons;

import core.buildings.Event;

import java.util.Date;

public class Patient extends Person {
    private boolean alive = true;

    public Patient() {
    }

    public Patient(String name, String surname) {
        super(name, surname);
    }

    public Patient(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
        super(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber);
    }

    public Patient(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
    }

    public Patient(String uniqueId) {
        super(uniqueId);
    }

    @Override
    public String[] getPersonInformation() {
        return new String[]{this.getUniqueId(), this.getName(), this.getSurname(),
                dateToString(this.getBirthdate()), this.getGender().toString(), this.getHomeAddress(),
                String.format("%d", this.getPhoneNumber())};
    }

    void setAlive(boolean alive) {
        this.alive = alive;
        notifyListeners(this, Event.UPDATE, null, null);
    }

    public boolean isAlive() {
        return alive;
    }

}
