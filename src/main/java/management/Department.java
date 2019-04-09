package management;

import java.util.ArrayList;

public class Department {

    private ArrayList<Person> staff = new ArrayList<Person>(1);
    private ArrayList<Person> patients = new ArrayList<Person>(1);

    public ArrayList<Person> getPatients() {
        return patients;
    }

    public ArrayList<Person> getStaff() {
        return staff;
    }

}
