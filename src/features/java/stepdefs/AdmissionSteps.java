package stepdefs;

import core.buildings.OutDepartment;
import core.persons.Doctor;
import core.persons.Patient;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import persistence.query_roles.QueryRoleClerk;
import persistence.query_roles.QueryRoleNurse;

import java.util.Calendar;

import static org.junit.Assert.*;

public class AdmissionSteps {
    private Doctor doctor = new Doctor("staff1");
    private OutDepartment outDepartment = new OutDepartment();
    private QueryRoleNurse nurse = new QueryRoleNurse();
    private Patient patient = new Patient("asdf");
    private QueryRoleClerk clerk = new QueryRoleClerk();

    @Given("a doctor")
    public void aDoctor() {
        doctor = new Doctor();
    }

    @When("there is waiting patients")
    public void thereIsWaitingPatients() {
        outDepartment.addWaitingPatient(patient);
        assertTrue(outDepartment.hasWaitingPatients());
    }

    @Then("he should call a patient")
    public void heShouldCallAPatient() {
        doctor.callWaitingPatient(outDepartment);
        assertTrue(doctor.getTreatedPatient().equals(patient));
    }

    @Given("an out department")
    public void anOutDepartment() {
        assertNotNull(outDepartment);
    }

    @And("a nurse")
    public void aNurse() {
        assertNotNull(nurse);
    }

    @And("a patient")
    public void aPatient() {
        Calendar cal = Calendar.getInstance();
        cal.set(420, Calendar.APRIL, 20);

        patient = new Patient(
                "pat1",
                "Emil",
                "pølz Ballermann",
                cal.getTime(),
                0,
                "DirtyStinkingBass, 4 tv",
                42042042
        );
    }

    @When("the clerk registers the patient to the system")
    public void theClerkRegistersThePatientToTheSystem() {
        assertTrue(clerk.registerPerson(patient, outDepartment));
    }

    @Then("the nurse should assign the patient to the waiting room")
    public void theNurseShouldAssignThePatientToTheWaitingRoom() {
        nurse.assignPatientToWaitingRoom(patient, outDepartment);
        assertEquals(patient, outDepartment.getNextWaitingPatient());
        assertNull(outDepartment.getNextWaitingPatient());
    }
}
