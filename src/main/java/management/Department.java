package management;

import java.util.ArrayList;

public class Department extends Hospital {

	// Setters
	private String name;
	private int capacity;
	private ArrayList<Bed> available = new ArrayList<Bed>();
	public ArrayList<Patient> patients = new ArrayList<Patient>();
	public ArrayList<Staff> staff = new ArrayList<Staff>();
	//management.Bed[capacity] available;
	
	public Department(int capacity, String name) {
		this.name = name;
		this.capacity = capacity;
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
		return capacity-available.size()>0;
	}
	public void assign(Patient p, Bed b) {
		b.add(p);
		// bind both patient to bed and bed to patient?
		//p.add(b)		THIS IS WEIRD, but needed in move
	}
	public void move(Patient p, Bed b) {
		//p.removeBed()
		b.add(p);
		//p.add(b)
	}
	public void remove(Patient p, Bed b) {
		b.remove();
		//p.removeBed()
	}
	public int getCapacity() {
		return capacity;
	}


}
