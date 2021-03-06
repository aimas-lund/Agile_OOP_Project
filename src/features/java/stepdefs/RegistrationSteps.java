package stepdefs;

import core.buildings.Department;
import core.buildings.OutDepartment;
import core.persons.*;
import core.utility.Gender;
import core.utility.InformationGenerator;
import core.utility.Speciality;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exceptions.PersonNotFoundException;
import org.junit.After;
import persistence.query_roles.QueryRoleClerk;
import persistence.query_roles.QueryRoleICT;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class RegistrationSteps {
    private Patient registeredPatient;
    private Patient unregisteredPatient;
    private Department department = new OutDepartment("nowIhaveAnID", "nameWithName");
    private QueryRoleClerk clerk = new QueryRoleClerk();
    private QueryRoleICT ictOfficer = new QueryRoleICT();
    private Staff registeredStaff = new Clerk("regStaff");
    private Staff unregisteredStaff = new Clerk("unRegStaff");
    private Doctor doctor = new Doctor("doc1");

    @Before
    public void setUp() {
        registeredPatient = new Patient(
                "regpat1",
                "Thor",
                "Odin",
                new Date(1337),
                Gender.FEMALE,
                "Asgaard 23",
                45231232);
        unregisteredPatient = new Patient(
                "unregpat2",
                "Dank",
                "Meister",
                new Date(420),
                Gender.MALE,
                "Groove street 23",
                13371337);
    }

    @After
    public void tearDown() {
        ictOfficer.delete(registeredStaff, department);
        ictOfficer.delete(unregisteredStaff, department);
        ictOfficer.delete(doctor, department);
        clerk.delete(registeredPatient, department);
        clerk.delete(unregisteredPatient, department);
    }

    @Given("a new patient")
    public void aNewPatient() {
        // Get old patient
        Patient oldPatient = this.registeredPatient;

        // Make new patient
        this.registeredPatient = new Patient("testID",
                "Freja",
                "Sif",
                new Date(230),
                Gender.FEMALE,
                "Asgaard 16",
                45231252);

        // Check that they aren't the same
        assertNotSame(oldPatient, registeredPatient);
    }

    @When("the patient walks into the department")
    public void thePatientWalksIntoTheDepartment() {
        // Can't really add code to this
    }

    @Then("clerk should register patient information")
    public void clerkShouldRegisterPatientInformation() {
        // Testing purposes
        Calendar cal = Calendar.getInstance();
        cal.set(1995, Calendar.APRIL, 20);

        // Clerk needs registeredPatient information
        registeredPatient = new Patient(
                "pat1123",
                "Emil",
                "pølz Ballermann",
                cal.getTime(),
                Gender.FEMALE,
                "DirtyStinkingBass, 4 tv",
                42042042
        );

        // Clerk registers registeredPatient
        clerk.registerPerson(registeredPatient, department);

        // Check that the registeredPatient is added to department and others are not
        assertEquals(registeredPatient, department.getPatients().get(0));
        assertNotEquals(unregisteredPatient, department.getPatients().get(0));

        clerk.delete(registeredPatient, department);
    }

    @Given("an already registered patient")
    public void anAlreadyRegisteredPatient() {
        // Register patient to department (without information)
        clerk.registerPerson(registeredPatient, department);
        assertTrue(department.isPatientInDepartment(registeredPatient));
    }

    @When("the patient goes to the hospital")
    public void thePatientGoesToTheHospital() {
        // Can't add code here
    }

    @Then("the clerk should be able to check if the patient is registered")
    public void theClerkShouldBeAbleToCheckIfThePatientIsRegistered() {
        // Check that the registered patient is there and not the unregistered one
        assertTrue(clerk.isPersonRegistered(registeredPatient, department));
        assertFalse(clerk.isPersonRegistered(unregisteredPatient, department));
        clerk.delete(registeredPatient, department);

    }

    @Then("the patient should get a unique ID")
    public void thePatientShouldGetAUniqueID() {
        // Clerk registers 2 patients, and they get a unique ID
        clerk.registerPerson(registeredPatient, department);
        clerk.registerPerson(unregisteredPatient, department);  // Don't mind the name

        // Check that the registeredPatients have unique ids
        assertNotEquals(registeredPatient.getUniqueId(), unregisteredPatient.getUniqueId());

        clerk.delete(registeredPatient, department);
        clerk.delete(unregisteredPatient, department);
    }

    @Given("a newly hired employee")
    public void aNewlyHiredEmployee() {
        // Get old ICTOfficer
        QueryRoleICT oldIctOfficer = this.ictOfficer;

        // Make new ICTOfficer
        this.ictOfficer = new QueryRoleICT();

        // Check that they aren't the same
        assertNotSame(oldIctOfficer, ictOfficer);
    }

    @When("walking in to the ICT officer's office")
    public void walkingInToTheICTOfficerSOffice() {
        // yeah yeah.. weird case
    }

    @Then("the ICT officer should register the staff information")
    public void theICTOfficerShouldRegisterTheStaffInformation() {
        // Testing purposes
        Calendar cal = Calendar.getInstance();
        cal.set(1997, Calendar.DECEMBER, 23);

        // Register staff
        registeredStaff = new Clerk("Billy",
                "Mcloving",
                cal.getTime(),
                Gender.MALE,
                "my house, middle of the street",
                51558588);


        ictOfficer.registerPerson(registeredStaff, department);

        // Check that staff has been registered
        List<Staff> staff = department.getStaff();
        assertEquals(registeredStaff, staff.get(0));

        ictOfficer.delete(registeredStaff, department);
    }

    @And("register a uniqueID")
    public void registerAUniqueID() {
        // Add unique IDs
//        ictOfficer.addUniqueIdToPerson(registeredStaff);
//        ictOfficer.addUniqueIdToPerson(unregisteredStaff);
//
        // Check that they are unique
        assertNotEquals(registeredStaff.getUniqueId(), unregisteredStaff.getUniqueId());
    }

    @And("work email should be generated")
    public void workEmailShouldBeGenerated() {
        // Set necessary information
        registeredStaff = new Clerk("Molly", "McLovin");
        unregisteredStaff = new Clerk("Emil", "Ballermann");

        // Generate emails
        String email1 = InformationGenerator.generateEmail(registeredStaff);
        String email2 = InformationGenerator.generateEmail(unregisteredStaff);

        // Check that they are different
        assertNotSame(email1, email2);

        // Check that they consist of their initials
        assertEquals("MOMC", email1.substring(0, 4));
        assertEquals("EMBA", email2.substring(0, 4));
    }

    @When("he is assigned a work email")
    public void he_is_assigned_a_work_email() {
        // Set necessary information
        registeredStaff = new Clerk("Solomun", "Bjergsen");

        // Generate email
        String email1 = InformationGenerator.generateEmail(registeredStaff);

        // Assign email
        new PersonInformationFacade(registeredStaff).setStaffEmail(email1);

        // Check it is stored and the correct initials are there
        assertEquals(registeredStaff.getEmail(), email1);
        assertEquals("SOBJ", email1.substring(0, 4));
    }

    @When("his initials overlap with someone else's")
    public void his_initials_overlap_with_someone_else_s() {
        // Set necessary information for overlap
        unregisteredStaff = new Clerk("Soren", "BjLamar");

        // Get initials directly
        String staff1_initials = unregisteredStaff.getName().substring(0, 2) + unregisteredStaff.getSurname().substring(0, 2);
        String staff2_initials = registeredStaff.getName().substring(0, 2) + registeredStaff.getSurname().substring(0, 2);

        // Check the initials overlap
        assertEquals(staff1_initials.toUpperCase(), staff2_initials.toUpperCase());
    }

    @Then("take next letter in his name")
    public void takeNextLetterInHisName() {
        // Get initials through helper class
        String staff1_email = InformationGenerator.generateEmail(registeredStaff);
        String staff2_email = InformationGenerator.generateEmail(unregisteredStaff);

        // Check that they are not equal
        assertNotEquals(staff1_email.substring(0, 4), staff2_email.substring(0, 4));
    }

    @Given("a new Doctor with specialization {string}")
    public void aNewDoctorWithSpecialization(String doctorSpeciality) {
        // Get speciality as enum
        Speciality speciality = null;

        for (Speciality spec : Speciality.values()) {
            if (spec.getSpecialty().equals(doctorSpeciality)) {
                speciality = spec;
            }
        }

        // Set date
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(1990, Calendar.APRIL, 20);

        // Set patient information
        doctor = new Doctor(speciality, "Mortimer", "Montgomery", cal.getTime(), Gender.MALE,
                "myhouse", 13371337);

    }

    @When("being registered as a staff member")
    public void beingRegisteredAsAStaffMember() {
        // Register staff to department
        ictOfficer.registerPerson(doctor, department);

        // Check that he is registered
        assertSame(department.getStaff().get(0), doctor);
    }

    @Then("their specialization should be specified saved in the database")
    public void theirSpecializationShouldBeSpecifiedSavedInTheDatabase() {
        // Check that some information has been set
        try {
            Doctor doctor = ictOfficer.find(this.doctor);
            assertNotNull(doctor.getSpeciality());
            ictOfficer.delete(doctor, department);
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Given("a clerk")
    public void aClerk() {
        // Check that there is a clerk
        assertNotNull(clerk);
    }

    @And("a registered staff member")
    public void aStaffRegisteredMember() {
        // Check that there is a staff member
        assertNotNull(registeredStaff);

        registeredStaff = new Clerk("Biver", "Thomsen", new Date(2000), Gender.FEMALE, "mithjem 23", 43236581);
    }

    @When("they are already in the system")
    public void theyAreAlreadyInTheSystem() {
        // Register staff
        ictOfficer.registerPerson(registeredStaff, department);

        // Check that the staff is registered
        assertTrue(ictOfficer.isPersonRegistered(registeredStaff, department));
    }

    @And("trying to register the staff member")
    public void tryingToRegisterTheStaffMember() {
        // Old staff size
        int oldStaffSize = department.getStaff().size();

        // Register and check that it fails
        assertFalse(ictOfficer.registerPerson(registeredStaff, department));

        // Check that the size hasn't changed
        assertEquals(department.getStaff().size(), oldStaffSize);
    }

    @Then("the system displays that this member profile is already created")
    public void theSystemDisplaysThatThisMemberProfileIsAlreadyCreated() {
        // Check that we get an exception
        assertTrue(ictOfficer.isPersonRegistered(registeredStaff, department));

        ictOfficer.isPersonRegistered(registeredStaff, department);
        ictOfficer.delete(registeredStaff, department);

    }

}
