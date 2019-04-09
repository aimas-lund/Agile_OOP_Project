package management;

public class Doctor extends Staff {
    private Speciality speciality;

    public Doctor() {
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}
