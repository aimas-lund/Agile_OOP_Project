package core.persons;

import java.util.Date;

public class ICTOfficer extends Staff {

    public ICTOfficer(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber, String email, String initials) {
        super(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
    }

    public ICTOfficer(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
    }

    public ICTOfficer(String uniqueId) {
        super(uniqueId);
    }

    public ICTOfficer() {

    }
}
