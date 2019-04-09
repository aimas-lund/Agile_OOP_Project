package management;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private List<Person> staff = new ArrayList<Person>(1);
    private List<Person> patients = new ArrayList<Person>(1);

    public List<Person> getPatients() {
        return patients;
    }

    public List<Person> getStaff() {
        return staff;
    }

}
