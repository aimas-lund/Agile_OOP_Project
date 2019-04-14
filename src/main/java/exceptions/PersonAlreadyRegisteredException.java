package exceptions;

public class PersonAlreadyRegisteredException extends Exception {
    public PersonAlreadyRegisteredException(String message) {
        super(message);
    }
}
