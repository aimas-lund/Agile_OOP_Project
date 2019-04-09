package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.Clerk;
import management.ICTOfficer;
import management.Patient;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class ChangeInformationSteps {
    private ICTOfficer ictOfficer = new ICTOfficer();
    private Clerk clerk = new Clerk();
    private Patient patientWithInformation = new Patient();
    private Patient patientWithoutInformation = new Patient();

    @Given("an ICT officer")
    public void anICTOfficer() {
        // An ICT officer exists
        assertNotNull(ictOfficer);
    }

    @And("a person with information")
    public void aPersonWithInformation() {
        // Set information
        ictOfficer.setPersonInformation(patientWithInformation,
                "Kaj",
                "Andreasen",
                new GregorianCalendar(2000, Calendar.JANUARY, 0).getTime(),
                0,
                "VejenPåGaden",
                42013370
        );

        // Check that some values are not null
        assertNotNull(patientWithInformation.getName());
        assertNotNull(patientWithInformation.getSurname());
        assertNotNull(patientWithInformation.getBirthdate());
        assertNotNull(patientWithInformation.getHomeAddress());
    }

    @When("he gets new PI about a person")
    public void heGetsNewPIAboutAPerson() {
        // Patient changed phone number

        // can't write code for this
    }

    @Then("he should change the information")
    public void heShouldChangeTheInformation() {
        // Get old values
        int oldPhoneNumber = patientWithInformation.getPhoneNumber();

        // Set new values
        ictOfficer.setPersonPhoneNumber(patientWithInformation,
                13371337);

        // Check that the value has changed
        assertNotEquals(oldPhoneNumber, patientWithInformation.getPhoneNumber());
        assertEquals(13371337, patientWithInformation.getPhoneNumber());
    }

    @When("the information is wrong")
    public void theInformationIsWrong() {
        // Patient is born in year 1990 and address needs to be changed..
        // can't write code
    }

    @Then("the clerk should be able to change this information")
    public void theClerkShouldBeAbleToChangeThisInformation() {
        // Get old values
        Date oldDate = patientWithInformation.getBirthdate();
        String oldAddress = patientWithInformation.getHomeAddress();

        // Set new values
        clerk.setPersonBirthdate(patientWithInformation,
                new GregorianCalendar(1990, Calendar.JANUARY, 0).getTime());
        clerk.setPersonHomeAddress(patientWithInformation,
                "Vejen på gaden 10");

        // Check that the values have changed
        assertNotEquals(oldAddress, patientWithInformation.getHomeAddress());
        assertNotEquals(oldDate, patientWithInformation.getBirthdate());
        assertEquals("Vejen på gaden 10", patientWithInformation.getHomeAddress());
        Calendar cal = Calendar.getInstance();
        cal.setTime(patientWithInformation.getBirthdate());
        assertEquals(1990, cal.get(Calendar.YEAR));
    }
}
