package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exceptions.PersonAlreadyRegisteredException;
import management.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class RegistrationSteps {
    private Patient registeredPatient = new Patient();
    private Patient unregisteredPatient = new Patient();
    private Department department = new Department();
    private Clerk clerk = new Clerk();
    private ICTOfficer ictOfficer = new ICTOfficer();
    private Staff registeredStaff = new Staff();
    private Staff unregisteredStaff = new Staff();
    private Doctor doctor = new Doctor();

    @Given("a new patient")
    public void aNewPatient() {
        // Get old patient
        Patient oldPatient = this.registeredPatient;

        // Make new patient
        this.registeredPatient = new Patient();

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

        // management.Clerk needs registeredPatient information
        clerk.setPersonInformation(registeredPatient,
                "Emil",
                "pølz Ballermann",
                cal.getTime(),
                0,
                "DirtyStinkingBass, 4 tv",
                42042042
        );

        // management.Clerk registers registeredPatient
        clerk.registerPerson(registeredPatient, department);

        // Check that the registeredPatient is added to department and others are not
        assertEquals(registeredPatient, department.getPatients().get(0));
        assertNotEquals(unregisteredPatient, department.getPatients().get(0));
    }

    @Given("an already registered patient")
    public void anAlreadyRegisteredPatient() {
        // Register patient to department (without information)
        clerk.registerPerson(registeredPatient, department);
        assertTrue(department.getPatients().contains(registeredPatient));
    }

    @When("the patient goes to the hospital")
    public void thePatientGoesToTheHospital() {
        // Can't add code here
    }

    @Then("the clerk should be able to check if the patient is registered")
    public void theClerkShouldBeAbleToCheckIfThePatientIsRegistered() {
        // Check that the registered patient is there and not the unregistered one
        assertTrue(clerk.checkPatientRegistrationStatus(registeredPatient, department));
        assertFalse(clerk.checkPatientRegistrationStatus(unregisteredPatient, department));

    }

    @Then("the patient should get a unique ID")
    public void thePatientShouldGetAUniqueID() {
        // Testing purposes
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(1995, Calendar.APRIL, 20);

        // Clerk registers 2 patients, and they get a unique ID
        clerk.registerPerson(registeredPatient, department);
        clerk.registerPerson(unregisteredPatient, department);  // Don't mind the name

        // Check that the registeredPatients have unique ids
        assertNotEquals(registeredPatient.getUniqueId(), unregisteredPatient.getUniqueId());
    }

    @Given("a newly hired employee")
    public void aNewlyHiredEmployee() {
        // Get old ICTOfficer
        ICTOfficer oldIctOfficer = this.ictOfficer;

        // Make new ICTOfficer
        this.ictOfficer = new ICTOfficer();

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
        ictOfficer.setPersonInformation(registeredStaff,
                "Billy",
                "Mcloving",
                cal.getTime(),
                0,
                "my house, middle of the street",
                51558588);                      // call me <- remove at some point :))


        ictOfficer.registerPerson(registeredStaff, department);

        // Check that staff has been registered
        List<Person> staff = department.getStaff();
        assertEquals(registeredStaff, staff.get(0));
    }

    @And("register a uniqueID")
    public void registerAUniqueID() {
        // Add unique IDs
        ictOfficer.addUniqueIdToPerson(registeredStaff);
        ictOfficer.addUniqueIdToPerson(unregisteredStaff);

        // Check that they are unique
        assertNotEquals(registeredStaff.getUniqueId(), unregisteredStaff.getUniqueId());
    }

    @And("work email should be generated")
    public void workEmailShouldBeGenerated() {
        // Set necessary information
        ictOfficer.setPersonName(registeredStaff, "Billy");
        ictOfficer.setPersonSurname(registeredStaff, "McLovin");
        ictOfficer.setPersonName(unregisteredStaff, "Emil");
        ictOfficer.setPersonSurname(unregisteredStaff, "Ballermann");

        // Generate emails
        String email1 = InformationGenerator.generateEmail(registeredStaff);
        String email2 = InformationGenerator.generateEmail(unregisteredStaff);

        // Check that they are different
        assertNotSame(email1, email2);

        // Check that they consist of their initials
        assertEquals("BIMC", email1.substring(0, 4));
        assertEquals("EMBA", email2.substring(0, 4));
    }

    @When("he is assigned a work email")
    public void he_is_assigned_a_work_email() {
        // Set necessary information
        ictOfficer.setPersonName(registeredStaff, "Solomun");
        ictOfficer.setPersonSurname(registeredStaff, "Bjergsen");

        // Generate email
        String email1 = InformationGenerator.generateEmail(registeredStaff);

        // Assign email
        registeredStaff.setEmail(email1);

        // Check it is stored and the correct initials are there
        assertEquals(registeredStaff.getEmail(), email1);
        assertEquals("SOBJ", email1.substring(0, 4));
    }

    @When("his initials overlap with someone else's")
    public void his_initials_overlap_with_someone_else_s() {
        // Set necessary information for overlap
        ictOfficer.setPersonName(unregisteredStaff, "Soren");
        ictOfficer.setPersonSurname(unregisteredStaff, "BjLamar");

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

    @Given("a new Doctor")
    public void aNewDoctor() {
        // Get old doctor
        Doctor oldDoctor = this.doctor;

        // Make new doctor
        this.doctor = new Doctor();

        // Check that they aren't the same
        assertNotSame(oldDoctor, doctor);
    }

    @When("being registered as a staff member")
    public void beingRegisteredAsAStaffMember() {
        // Register staff to department
        ictOfficer.registerPerson(doctor, department);

        // Check that he is registered
        assertSame(department.getStaff().get(0), doctor);
    }

    @Then("their specialization {string} should be specified along other information")
    public void theirSpecializationShouldBeSpecifiedAlongOtherInformation(String doctorSpeciality) {
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
        ictOfficer.setDoctorInformation(doctor, speciality, "Mortimer", "Montgomery", cal.getTime(), 0,
                "myhouse", 13371337);

        // Check that some information has been set
        assertEquals(speciality, doctor.getSpeciality());
        assertEquals("Mortimer", doctor.getName());
        assertEquals(13371337, doctor.getPhoneNumber());
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
    }

    @When("they are already in the system")
    public void theyAreAlreadyInTheSystem() {
        // Register staff
        ictOfficer.registerPerson(registeredStaff, department);

        // Check that the staff is registered
        assertSame(registeredStaff, department.getStaff().get(0));
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
        assertThrows(PersonAlreadyRegisteredException.class, () -> ictOfficer.isPersonRegistered(registeredStaff, department));

        try {
            ictOfficer.isPersonRegistered(registeredStaff, department);
        } catch (PersonAlreadyRegisteredException e) {
            assertTrue(e.getMessage().contains("is already registered in the system"));
        }

    }
}
