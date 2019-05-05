package tests.core.utility;

import core.persons.Clerk;
import core.persons.Staff;
import core.utility.InformationGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InformationGeneratorTest {

    private Staff person = new Clerk("Hjort", "Uran");
    private Staff person1 = new Clerk("Hjort", "Uran");
    private Staff person2 = new Clerk("Hjort", "Uran");

    @Test
    public void generateEmailWithTwoOverlappingInitials() {
        assertEquals("HJUR", InformationGenerator.generateEmail(person).substring(0, 4));
        assertEquals("HJUA", InformationGenerator.generateEmail(person1).substring(0, 4));
        assertEquals("HJUN", InformationGenerator.generateEmail(person2).substring(0, 4));
    }
}