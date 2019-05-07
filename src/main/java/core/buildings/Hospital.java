package core.persons;

import core.buildings.Department;
import core.persons.Patient;
import core.persons.Staff;
import core.utility.InformationGenerator;
import persistence.data_access_objects.DaoDepartmentImpl;

import java.util.ArrayList;
import java.util.HashMap;

import static core.buildings.Event.ADD;
import static core.buildings.Event.DELETE;

public class Hospital {
    private ArrayList<Department> departments = new ArrayList<>();

    public Hospital() {
    }

    public boolean add(Department department) {
        if (departments.contains(department)) {
            return false;
        }
        departments.add(department);
        department.notifyListeners(department, ADD, department, department);
        return true;
    }

    public boolean remove(Department department) {
        if (!departments.contains(department)) {
            return false;
        }
        departments.remove(department);
        department.notifyListeners(department, DELETE, department, department);
        return true;
    }

    public boolean assign(Patient patient, Department department) {
        if (departments.contains(department)) {
            department.add(patient);
            return true;
        }
        return false;
    }

    public boolean assign(Staff staff, Department department) {
        if (departments.contains(department)) {
            department.add(staff);
            return true;
        }
        return false;
    }

    public boolean remove(Patient patient, Department department) {
        if (departments.contains(department)) {
            department.remove(patient);
            return true;
        }
        return false;
    }

    public boolean remove(Staff staff, Department department) {
        if (departments.contains(department)) {
            department.remove(staff);
            return true;
        }
        return false;
    }

    public boolean move(Patient patient, Department departmentFrom, Department departmentTo) {
        if (departmentFrom.getPatients().contains(patient) && !(departmentTo.getPatients().contains(patient))) {
            departmentFrom.remove(patient);
            departmentTo.add(patient);
            return true;
        }
        return false;
    }

    public boolean move(Staff staff, Department departmentFrom, Department departmentTo) {
        if (departmentFrom.getStaff().contains(staff) && !(departmentTo.getStaff().contains(staff))) {
            departmentTo.add(staff);
            departmentFrom.remove(staff);
            return true;
        }
        return false;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

}
