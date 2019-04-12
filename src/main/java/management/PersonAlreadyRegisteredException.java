package management;

public class PersonAlreadyRegisteredException extends Exception {
    PersonAlreadyRegisteredException(String message) {
        super(message);
    }
}
