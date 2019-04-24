package stepdefs;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import management.Clerk;
import management.Department;
import management.Patient;
import storage.Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseSteps {

    private Clerk clerk;
    private Patient patient;
    private Department department;

    @Before
    public void setUp() {
        department = new Department("Mockdepartment", 10);
        clerk = new Clerk();
        patient = new Patient();
    }
    @Given("a user")
    public void a_user() {
        assertNotNull(clerk);
    }

    @When("a new patient is admitted to the hospital")
    public void a_new_patient_is_admitted_to_the_hospital() {
        patient = new Patient(
                "Bobby",
                "Fischer",
                new Date(2019),
                0,
                "Homestreet 23",
                45231298);

        clerk.registerPerson(patient, department);
    }

    @Then("the user should add the patient to the database")
    public void the_user_should_add_the_patient_to_the_database() {
        Database database = new Database();
        Statement statement = database.createStatement();

        assertDoesNotThrow(() -> {
            String sql = "select * from patients where uniqueId = '%s'";
            sql = String.format(sql, patient.getUniqueId());
            ResultSet rs = statement.executeQuery(sql);

            assertTrue(rs.next());
            while (rs.next()) {
                assertEquals("Bobby", rs.getString("name"));
                assertEquals("Fischer", rs.getString("surname"));
            }

        });
    }
}
