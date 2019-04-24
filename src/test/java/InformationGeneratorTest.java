import management.InformationGenerator;
import management.Patient;
import management.Person;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InformationGeneratorTest {

    private Person person = new Patient("Hjort", "Uran");
    private Person person1 = new Patient("Hjort", "Uran");
    private Person person2 = new Patient("Hjort", "Uran");

    @Test
    public void generateEmailWithTwoOverlappingInitials() {
        assertEquals("HJUR", InformationGenerator.generateEmail(person).substring(0, 4));
        assertEquals("HJUA", InformationGenerator.generateEmail(person1).substring(0, 4));
        assertEquals("HOUA", InformationGenerator.generateEmail(person2).substring(0, 4));
    }
}