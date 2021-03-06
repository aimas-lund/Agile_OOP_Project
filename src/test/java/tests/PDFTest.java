package tests;

import com.itextpdf.text.DocumentException;
import core.buildings.Department;
import core.buildings.ERDepartment;
import core.utility.Gender;
import core.persons.Patient;
import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import core.utility.PersonToPdf;

import static core.utility.Gender.FEMALE;
import static junit.framework.TestCase.assertTrue;

public class PDFTest {
    @Test
    public void generatePDF() throws DocumentException, IOException, URISyntaxException {
        Patient patient1 = new Patient("iq", "OLINE", "STÆRKE", new Date(631152000000L), FEMALE, "Lærkevej 12", 21565656);
        Patient patient2 = new Patient("iq2", "NATASHA", "STÆRKE", new Date(631152000000L), Gender.FEMALE, "Lærkevej 12", 21565656);

        Department department = new ERDepartment("IQ2","ER2");
        department.add(patient1);
        department.add(patient2);

        PersonToPdf PTP = new PersonToPdf();
        PTP.PatientToPdf(department);

        department.remove(patient1);
        department.remove(patient2);
    }

}
