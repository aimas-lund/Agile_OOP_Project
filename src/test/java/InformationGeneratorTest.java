package tests;

import management.InformationGenerator;
import management.Patient;
import management.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InformationGeneratorTest {
    Person person = new Patient("Ole", "Bajersen");
    Person person1 = new Patient("Ole", "Ballermann");
    Person person2 = new Patient("Ole", "Baldur");

    @Test
    void generateEmailWithTwoOverlappingInitials() {
        assertEquals("OLBA", InformationGenerator.generateEmail(person).substring(0, 4));
        assertEquals("OLBL", InformationGenerator.generateEmail(person1).substring(0, 4));
        assertEquals("OEBL", InformationGenerator.generateEmail(person2).substring(0, 4));
    }
}