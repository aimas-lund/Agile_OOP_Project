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
	public boolean remove() {
		if (this.p == null) {
			return false;
		}
		this.occupied = false;
		this.p = null;
		return true;
	}
	// NEW 
	public boolean occupied() {
		return occupied;
	}
	public Patient getPatient() {
		return p;
	}
    public int getID() {
		return id;
	}
}
