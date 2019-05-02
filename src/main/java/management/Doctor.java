package management;

import java.util.Date;

public class Doctor extends Staff {
    private Speciality speciality;

    public Doctor() {
    }

    public Doctor(String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
    }

    public Doctor(Speciality speciality, String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
        this.speciality = speciality;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
