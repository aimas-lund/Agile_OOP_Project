import management.InformationGenerator;
import management.Patient;
import management.Person;
import org.junit.Test;

class InformationGeneratorTest {
    private Person person = new Patient("Ole", "Bajersen");
    private Person person1 = new Patient("Ole", "Ballermann");
    private Person person2 = new Patient("Ole", "Baldur");


    // TODO: Fix this test
    //@Test
    //void generateEmailWithTwoOverlappingInitials() {
    //    assertEquals("OLBA", InformationGenerator.generateEmail(person).substring(0, 4));
    //    assertEquals("OLBL", InformationGenerator.generateEmail(person1).substring(0, 4));
    //    assertEquals("OEBL", InformationGenerator.generateEmail(person2).substring(0, 4));
    //}
}