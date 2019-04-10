package management;

public class Bed {

    private Boolean occupied;
    private int id;
    private Patient patient;

    public Bed(int i) {
        occupied = false;
        id = i;
    }

    public void empty() {
        occupied = false;
        patient = null;
    }

    public Boolean fill(Patient p) {
        if (occupied) {
            return false;
        } else {
            occupied = true;
            patient = p;
            return true;
        }
    }

    public Boolean isoccupied() {
        return occupied;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getId() {
        return id;
    }
}
