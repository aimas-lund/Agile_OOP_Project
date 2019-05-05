package core.utility;

import core.persons.Person;
import core.persons.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InformationGenerator {
    private static String name;
    private static String surname;
    private static List<String> initials = new ArrayList<>(1);

    private static void getPersonInformation(Person person) {
        name = person.getName();
        surname = person.getSurname();
    }

    private static String generateInitials(Person person) {
        // TODO: Get all initials from database
        getPersonInformation(person);
        String inits;

        for (char nameChar : name.substring(1).toCharArray()) {
            for (char surnameChar : surname.substring(1).toCharArray()) {
                inits = (name.substring(0, 1) + nameChar + surname.charAt(0) + surnameChar).toUpperCase();
                if (!initials.contains(inits)) {
                    initials.add(inits);
                    return inits;
                }
            }
        }
        return null;
    }

    public static String generateEmail(Staff staff) {
        String initials = generateInitials(staff);
        return initials + "@agile_hospital.com";
    }

    public static String generateUniqueID() {
        return UUID.randomUUID().toString();
    }
}
