public class Bed {

    private Boolean occupied;
    public int id;
    private Patient patient;

    public Bed(int i) {
        occupied = false;
        id = i;
    }

    public void empty() {
        occupied = false;
        patient = null;
    }

    public void fill(Patient p) {
        patient = p;
    }

    public Boolean isoccupied() {
        return occupied;
    }
}
