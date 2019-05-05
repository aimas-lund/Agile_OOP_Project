package core.persons;

import java.util.Date;

public class Nurse extends Staff {
    public Nurse(String uniqueId) {
        super(uniqueId);
    }

    public Nurse() {
    }

    public Nurse(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
    }

    public Nurse(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber, String email, String initials) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
    }

    public Nurse(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber, String email, String initials) {
        super(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
    }

    public Nurse(String name, String surname) {
        super(name, surname);
    }


}
