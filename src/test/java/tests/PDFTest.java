package tests;

import com.itextpdf.text.DocumentException;
import management.Patient;
import org.junit.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import management.PersonToPdf;

import static junit.framework.TestCase.assertTrue;

public class PDFTest {
    @Test
    public void generatePDF() throws DocumentException, IOException, URISyntaxException {
        Patient patient = new Patient("iq", "OLINE", "STÆRKE", new Date(01 / 02 / 2018), 0, "Lærkevej 12", 21565656);
        PersonToPdf PTP = new PersonToPdf();
        PTP.PatientToPdf(patient);

    }

}
