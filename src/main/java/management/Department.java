package management;

import exceptions.exceededCapacityException;

import java.util.ArrayList;

public class Department extends Hospital {

	// Setters
	private String name;
	private int capacity;
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private ArrayList<Staff> staff = new ArrayList<Staff>();
	private Bed beds[];

	// PatientNotFoundException && StaffNotFoundException
	// Throw exception if not part of Department, otherwise just pass
	public Department(String name,int capacity) {
		this.name = name;
		this.capacity = capacity;
		this.beds = new Bed[capacity];
		for (int id = 0; id < beds.length; id++) {
			beds[id] = new Bed(id);
		}
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
	public boolean available_beds() {
		for (Bed bed : beds) {
			if (bed.getPatient() == null) return true;
		}
		return false;
	}
	public void assign(Patient p) throws exceededCapacityException {
		if (available_beds()) {
			for (Bed bed : beds) {
				if (bed.getPatient() == null) {
					bed.add(p);
					break;
				}
			}
		} else {
			throw new exceededCapacityException("No available beds");
		}
	}
	public void assign(Patient p, int id) throws UnavailableBedException, BedNotFoundException {

		Bed bed = beds[id];
		bed.add(p);
	}
	public void move(Patient p) throws exceededCapacityException {
		for (Bed bed : beds) {
			if (bed.getPatient() == null) bed.add(p); return;
		}
	}
	public void move(Patient p, int id) throws SameBedException, UnavailableBedException, BedNotFoundException {
		int oldID = -1;
		for (Bed bed : beds) {
			if (bed.getPatient() == p) {
				oldID = bed.getID();
				bed.remove();
			}
		}
		if (oldID == id) {
			throw new SameBedException();
			return;
		}
		assign(p,id);
	}
	public void removeFromBed(Patient p) throws PatientNotFoundException {
		for (Bed bed : beds) {
			if (bed.getPatient() == p) bed.remove();
		}
	}
	public String getName() {
		return name;
	}
	public int getCapacity() {
		return capacity;
	}
	public Bed[] getBeds() {
		return beds;
	}
	public int getAvailableBeds() {
		int available = 0;
		for (Bed bed : beds) {
			if (bed.getPatient() == null) available++;
		}
		return available;
	}
	public ArrayList getPatients() {
		return patients;
	}
	public ArrayList getStaff() {
		return staff;
	}

	// Patient added to 2 beds


}
