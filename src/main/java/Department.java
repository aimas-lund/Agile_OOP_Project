import java.util.ArrayList;

public class Department extends Hospital {
	private String name;
	private int capacity;
	private ArrayList<Bed> available = new ArrayList<Bed>();
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private ArrayList<Staff> staff = new ArrayList<Staff>();
	//Bed[capacity] available;
	
	Department(int capacity, String name) {
		this.name = name;
		this.capacity = capacity;
	}
	void add(Staff s) {
		staff.add(s);
	}
	void add(Patient p) {
		patients.add(p);
	}
	void remove(Staff s) {
		staff.remove(s);
	}
	void remove(Patient p) {
		patients.remove(p);
	}
	boolean available_beds() {
		return capacity-available.size()>0;
	}
	void assign(Patient p, Bed b) {
		b.add(p);
		// bind both patient to bed and bed to patient?
		//p.add(b)		THIS IS WEIRD, but needed in move
	}
	void move(Patient p, Bed b) {
		//p.removeBed()
		b.add(p);
		//p.add(b)
	}
	void remove(Patient p, Bed b) {
		b.remove();
		//p.removeBed()
	}

}
