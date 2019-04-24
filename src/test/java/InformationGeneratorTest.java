import management.InformationGenerator;
import management.Patient;
import management.Person;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class InformationGeneratorTest {
    private Person person = new Patient("Ole", "Bajersen");
    private Person person1 = new Patient("Ole", "Ballermann");
    private Person person2 = new Patient("Ole", "Baldur");

    @Test
    void generateEmailWithTwoOverlappingInitials() {
        assertEquals("OLBA", InformationGenerator.generateEmail(person).substring(0, 4));
        assertEquals("OLBL", InformationGenerator.generateEmail(person1).substring(0, 4));
        assertEquals("OEBL", InformationGenerator.generateEmail(person2).substring(0, 4));
    }
}