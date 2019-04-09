package management;

public class InformationGenerator {
    private static String name;
    private static String surname;
    private static int uniqueId;

    static void getPersonInformation(Person person) {
        name = person.getName();
        surname = person.getSurname();
    }

    static String generateInitials(Person person) {
        getPersonInformation(person);
        return name.substring(0, 2).toUpperCase() + surname.substring(0, 2).toUpperCase();
    }

    public static String generateEmail(Person person) {
        String initials = generateInitials(person);

        return initials + "@agile_hospital.com";
    }

    public static int generateUniqueID() {
        uniqueId++;
        return uniqueId;
    }

}
