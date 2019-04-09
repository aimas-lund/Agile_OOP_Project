
public class Bed {
	private boolean occupied;
	private Patient p;
	
	Bed() {
		this.occupied = false;
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
	public Patient patient() {
		return p;
	}

}
