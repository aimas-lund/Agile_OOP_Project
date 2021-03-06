package core.buildings;

import core.persons.Patient;
import core.persons.Staff;
import java.util.ArrayList;

import static core.buildings.Event.ADD;
import static core.buildings.Event.DELETE;

public class Hospital {
    private ArrayList<Department> departments = new ArrayList<>();

    public Hospital() {
    }

    /**
     * Adds a department to the hospital and updates the database
     *
     * @param department with an id
     * @return true if department has been added and is not already a department of the hospital, false otherwise
     */
    public boolean add(Department department) {
        if (departments.contains(department)) {
            return false;
        }
        departments.add(department);
        department.notifyListeners(department, ADD, department, department);
        return true;
    }

    /**
     * Removes a department from the hospital and updates the database
     *
     * @param department with an id
     * @return true if department has been added and is not already a department of the hospital, false otherwise
     */
    public boolean remove(Department department) {
        if (!departments.contains(department)) {
            return false;
        }
        departments.remove(department);
        department.notifyListeners(department, DELETE, department, department);
        return true;
    }

    /**
     * Adds a patient to an existing department in the hospital and updates in the database
     *
     * @param patient with at least a unique id
     * @param department with at least a unique id
     * @return true if department is in the hospital and the patient was successfully added
     */
    public boolean assign(Patient patient, Department department) {
        if (departments.contains(department)) {
            department.add(patient);
            return true;
        }
        return false;
    }

    /**
     * Adds a staff to an existing department in the hospital and updates in the database
     *
     * @param staff with at least a unique id
     * @param department with at least a unique id
     * @return true if department is in the hospital and the staff was successfully added
     */
    public boolean assign(Staff staff, Department department) {
        if (departments.contains(department)) {
            department.add(staff);
            return true;
        }
        return false;
    }

    /**
     * Removes a patient to an existing department in the hospital and updates in the database
     *
     * @param patient with at least a unique id
     * @param department with at least a unique id
     * @return true if department is in the hospital and the patient was successfully added
     */
    public boolean remove(Patient patient, Department department) {
        if (departments.contains(department)) {
            department.remove(patient);
            return true;
        }
        return false;
    }

    /**
     * Removes a staff to an existing department in the hospital and updates in the database
     *
     * @param staff with at least a unique id
     * @param department with at least a unique id
     * @return true if department is in the hospital and the staff was successfully added
     */
    public boolean remove(Staff staff, Department department) {
        if (departments.contains(department)) {
            department.remove(staff);
            return true;
        }
        return false;
    }

    /**
     * Moves a patient from a department to another by first removing him and the adding
     *
     * @param patient with at least a unique id
     * @param departmentFrom with at least a unique id
     * @param departmentTo with at least a unique id
     * @return true if successfully moved and patient is in the from department and not in the new department
     */
    public boolean move(Patient patient, Department departmentFrom, Department departmentTo) {
        if (departmentFrom.getPatients().contains(patient) && !(departmentTo.getPatients().contains(patient))) {
            departmentFrom.remove(patient);
            departmentTo.add(patient);
            return true;
        }
        return false;
    }

    /**
     * Moves a staff member from a department to another by first removing him and the adding
     *
     * @param staff with at least a unique id
     * @param departmentFrom with at least a unique id
     * @param departmentTo with at least a unique id
     * @return true if successfully moved and patient is in the from department and not in the new department
     */
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
