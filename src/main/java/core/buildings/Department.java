package core.buildings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import core.persons.Patient;
import core.persons.Staff;

import java.util.ArrayList;

@JsonIgnoreProperties(value = {"departmentInformation"})

public abstract class Department {
	private String name;

	private String uniqueId;
	private ArrayList<Patient> patients = new ArrayList<>();
	private ArrayList<Staff> staff = new ArrayList<>();
	Department() {
	}

	Department(String uniqueId, String name) {
		this.uniqueId = uniqueId;
		this.name = name;
	}

	Department(String uniqueId, String name, ArrayList<Patient> patients, ArrayList<Staff> staff) {
		this(uniqueId, name);
		this.patients = patients;
		this.staff = staff;
	}

	public void add(Staff s) {
		staff.add(s);
	}

	public void add(Patient p) {
		patients.add(p);
	}

	public void remove(Staff s) {
		staff.remove(s);
	}

	public void remove(Patient p) {
		patients.remove(p);
	}

	public boolean isPatientInDepartment(Patient patient) {
		return patients.contains(patient);
	}

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

	void setName(String name) {
		this.name = name;
	}

	void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	void setStaff(ArrayList<Staff> staff) {
		this.staff = staff;
	}


}
