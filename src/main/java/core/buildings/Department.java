package core.buildings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.persons.Patient;
import core.persons.Staff;

import java.util.ArrayList;
import java.util.List;

import static core.buildings.Event.*;

@JsonIgnoreProperties(value = {"departmentInformation"})
public abstract class Department implements Observable {
	private String name;
    private String uniqueId;
	private ArrayList<Patient> patients = new ArrayList<>();
	private ArrayList<Staff> staff = new ArrayList<>();
    private List<Observer> listeners = new ArrayList<>();

	Department() {
        addChangeListener(new DepartmentObserver());
    }

	Department(String uniqueId, String name) {
        this();
        this.uniqueId = uniqueId;
        this.name = name;
    }

	Department(String uniqueId, String name, ArrayList<Patient> patients, ArrayList<Staff> staff) {
		this(uniqueId, name);
		this.patients = patients;
		this.staff = staff;
	}

    /**
     * Adds a staff with uniqueid to department and updates it in the database
     *
     * @param staff with at least uniqueid
     * @return
     */
    public boolean add(Staff staff) {
        if (staff.getUniqueId() == null) {
            return false;
        }

        if (!this.staff.contains(staff)) {
            this.staff.add(staff);
            notifyListeners(this, ADD, null, staff);
            return true;
        }
        return false;
    }

    /**
     * Adds a patient with uniqueid to department and updates it in the database corresponding to department type
     * and whether or not the patient is waiting or is in a bed
     *
     * @param patient with at least unique id
     * @return
     */
    public boolean add(Patient patient) {
        if (patient.getUniqueId() == null) {
            return false;
        }

        if (!patients.contains(patient)) {
            patients.add(patient);
            notifyListeners(this, ADD, null, patient);
            return true;
        }
        return false;
    }

    /**
     * Removes a staff member from the department and database
     *
     * @param staff with at least unique id
     */
    public void remove(Staff staff) {
        notifyListeners(this, DELETE, staff, null);
        this.staff.remove(staff);
    }

    /**
     * Removes a patient from the department and database
     *
     * @param patient with at least unique id
     */
    public void remove(Patient patient) {
        notifyListeners(this, DELETE, patient, null);
        patients.remove(patient);
	}

    /**
     * @param patient with at least unique id
     * @return true if patient is in department false otherwise
     */
	public boolean isPatientInDepartment(Patient patient) {
		return patients.contains(patient);
	}

    /**
     * @param staff with at least unique id
     * @return true if staff is in department false otherwise
     */
	public boolean isStaffInDepartment(Staff staff) {
		return this.staff.contains(staff);
	}

	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public ArrayList<Staff> getStaff() {
		return staff;
	}

	public String[] getDepartmentInformation() {
		return new String[]{this.uniqueId, this.name, this.getClass().getSimpleName()};
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public String getName() {
		return name;
	}

    /**
     * Sets name of department and updates in database
     *
     * @param name of person
     */
    void setName(String name) {
        this.name = name;
        notifyListeners(this, UPDATE, null, null);
    }

    /**
     * Sets uniqueId of department
     *
     * @param uniqueId of person
     */
    void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        notifyListeners(this, UPDATE, null, this);
    }

    /**
     * Adds many patients to department and adds all to database
     *
     * @param patients patients with at least unique id
     */
    void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
        for (Patient patient :
                patients) {
            notifyListeners(this, ADD, null, patient);
        }
    }

    /**
     * Adds many staff members to department and adds all to database
     *
     * @param staff staff members with at least unique id
     */
    void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
        for (Staff aStaff :
                staff) {
            notifyListeners(this, ADD, null, aStaff);
        }
    }

    @Override
    public void addChangeListener(Observer newListener) {
        listeners.add(newListener);
    }

    @Override
    public void removeChangeListener(Observer listener) {
        listeners.remove(listener);
    }

    @Override
    public void notifyListeners(Object source, Event event, Object oldValue, Object newValue) {
        for (Observer listener :
                listeners) {
            listener.objectChanged(source, event, oldValue, newValue);
        }
    }

}
