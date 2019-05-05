package core.persons;

import java.util.Date;

public class Nurse extends Staff {
    public Nurse(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber, String email, String initials) {
        super(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber, email, initials);
    }
}
