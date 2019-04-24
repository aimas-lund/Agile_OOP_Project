package management;

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
        getPersonInformation(person);
        String inits = name.substring(0, 2).toUpperCase() + surname.substring(0, 2).toUpperCase();

        int nameEndindex = 1;
        int surnameEndindex = 1;

        while (initials.contains(inits)) {
            if (nameEndindex >= surnameEndindex) {
                surnameEndindex++;
            } else {
                nameEndindex++;
            }

            // TODO: split into 4 characters and concat, always start at 0 index, only increment end index
            inits = (name.substring(0, 1) + name.charAt(nameEndindex) + surname.charAt(0) + surname.charAt(surnameEndindex));
            inits = inits.toUpperCase();
        }

        initials.add(inits);
        return inits;
    }

    public static String generateEmail(Person person) {
        String initials = generateInitials(person);
        return initials + "@agile_hospital.com";
    }

    static String generateUniqueID() {
        return UUID.randomUUID().toString();
    }

}
