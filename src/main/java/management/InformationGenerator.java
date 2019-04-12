package management;

import java.util.ArrayList;
import java.util.List;

public class InformationGenerator {
    private static String name;
    private static String surname;
    private static int uniqueId;
    private static List<String> initials = new ArrayList<String>(1);

    private static void getPersonInformation(Person person) {
        name = person.getName();
        surname = person.getSurname();
    }

    private static String generateInitials(Person person) {
        getPersonInformation(person);
        String inits = name.substring(0, 2).toUpperCase() + surname.substring(0, 2).toUpperCase();

        int nameEndindex = 2;
        int surnameEndindex = 2;

        while (initials.contains(inits)) {
            if (nameEndindex >= surnameEndindex) {
                surnameEndindex++;
            } else {
                nameEndindex++;
            }

            // TODO: split into 4 characters and concat, always start at 0 index, only increment end index
            inits = name.substring(nameEndindex - 2, nameEndindex).toUpperCase() +
                    surname.substring(surnameEndindex - 2, surnameEndindex).toUpperCase();
        }
        initials.add(inits);
        return inits;
    }

    public static String generateEmail(Person person) {
        String initials = generateInitials(person);

        return initials + "@agile_hospital.com";
    }

    static int generateUniqueID() {
        uniqueId++;
        return uniqueId;
    }

}
