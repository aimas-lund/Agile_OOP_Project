package management;

public class Bed {
	private boolean occupied;
	private Patient p;
	private int id;
	
	public Bed(int id) {
		this.occupied = false;
		this.id = id;
	}
	public void add(Patient p) {
		this.occupied = true;
		this.p = p;
	}
	public void remove() {
		this.occupied = false;
		this.p = null;
	}
	
	// NEW 
	public boolean occupied() {
		return occupied;
	}
	public Patient getPatient() {
		return p;
	}
    public int getID() { return id;}
}
