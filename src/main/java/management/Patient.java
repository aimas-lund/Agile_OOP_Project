package management;

public class Patient extends Person {
    private boolean alive = true;

    public Patient() {
    }

    public Patient(String name, String surname) {
        super(name, surname);
    }

    public boolean isAlive() {
        return alive;
    }

}
