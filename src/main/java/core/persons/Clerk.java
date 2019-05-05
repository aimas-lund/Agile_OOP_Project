package core.persons;

import java.util.Date;

public class Clerk extends Staff {
    public Clerk(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber, String email, String initials) {
        super(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
    }

    public Clerk(String name, String surname) {
        super(name, surname);
    }

    public Clerk() {

    }

    public Clerk(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
    }

    public Clerk(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber, String email, String initials) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
    }

    public Clerk(String uniqueId) {
        super(uniqueId);
    }
}
