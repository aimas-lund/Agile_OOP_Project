package tests;

import management.InformationGenerator;
import management.Staff;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InformationGeneratorTest {

    private Staff person = new Staff("Hjort", "Uran");
    private Staff person1 = new Staff("Hjort", "Uran");
    private Staff person2 = new Staff("Hjort", "Uran");

    @Test
    public void generateEmailWithTwoOverlappingInitials() {
        assertEquals("HJUR", InformationGenerator.generateEmail(person).substring(0, 4));
        assertEquals("HJUA", InformationGenerator.generateEmail(person1).substring(0, 4));
        assertEquals("HJUN", InformationGenerator.generateEmail(person2).substring(0, 4));
    }
}