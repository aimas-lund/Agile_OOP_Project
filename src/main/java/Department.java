import java.util.ArrayList;

public class Department extends Hospital {
	String name;
	int capacity;
	private ArrayList<Bed> available = new ArrayList<Bed>();
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private ArrayList<Staff> staff = new ArrayList<Staff>();
	//Bed[capacity] available;
	
	Department(int capacity, String name) {
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
	public int available_beds() {
		return capacity-available.size();
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
	
}
