package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.ICTOfficer;
import management.Patient;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ChangeInformationSteps {
    private ICTOfficer ictOfficer = new ICTOfficer();
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
        // Patient is born in year 1990 and address needs to be changed..

        // can't write code for this
    }

    @Then("he should change the information")
    public void heShouldChangeTheInformation() {
        // Get old values
        Date oldDate = patientWithInformation.getBirthdate();
        String oldAddress = patientWithInformation.getHomeAddress();

        // Set new values
        ictOfficer.setPersonBirthdate(patientWithInformation,
                new GregorianCalendar(1990, Calendar.JANUARY, 0).getTime());
        ictOfficer.setPersonHomeAddress(patientWithInformation,
                "Vejen på gaden 10");

        // Check that the values have changed
        assertNotEquals(oldAddress, patientWithInformation.getHomeAddress());
        assertNotEquals(oldDate, patientWithInformation.getBirthdate());
    }
}
