package management;

import java.util.ArrayList;

public abstract class Department {
	private String name;
    private String uniqueId;
	private ArrayList<Patient> patients = new ArrayList<>();
	private ArrayList<Staff> staff = new ArrayList<>();

    public Department() {
    }

    public Department(String uniqueId) {
		this.uniqueId = uniqueId;
	}

    public Department(String uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
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

    public ArrayList<Patient> getPatients() {
		return patients;
	}

    public ArrayList<Staff> getStaff() {
		return staff;
	}

	public String[] getDepartmentInformation() {
        return new String[]{this.getUniqueId(), this.getClass().getCanonicalName()};
	}

	void setUniqueId(String uniqueID) {
		this.uniqueId = uniqueID;
	}

	public String getUniqueId() {
		return uniqueId;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
