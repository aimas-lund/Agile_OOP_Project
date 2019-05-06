package core.buildings;

import core.persons.Patient;
import core.persons.Staff;

import java.util.ArrayList;

public class DepartmentInformationFacade {
    private final Department department;

    public DepartmentInformationFacade(Department department) {
        this.department = department;
    }

    public void setDepartmentName(String name) {
        department.setName(name);
    }

    public void setDepartmentUniqueId(String departmentUniqueId) {
        department.setUniqueId(departmentUniqueId);
    }

    public void setDepartmentPatients(ArrayList<Patient> patients) {
        department.setPatients(patients);
    }

    public void setDepartmentStaff(ArrayList<Staff> staff) {
        department.setStaff(staff);
    }
}
