package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.Department;

public class Departments {
    Department d = new Department(80,"ER");
    Patient p;
    Staff s;
    Bed b;
    @Given("a department")
    public void aDepartment() {
        assertTrue(d instanceof Department);
    }
    //[7] Capacity

    @When("the department is made")
    public void theDepartmentIsMade(int capacity, String name) {
        d = new Department(capacity,name);
    }

    @Then("the department needs to know how many patients they can host")
    public void theDepartmentNeedsToKnowHowManyPatientsTheyCanHost() {
        assertTrue(d.capacity > 0);
    }


    // [8] Available beds

    @When("a patient needs care")
    public void aPatientNeedsCare() {

    }

    @Then("the department needs to know if there's an available bed")
    public void theDepartmentNeedsToKnowIfThereSAnAvailableBed() {
        assertTrue(d.available_beds());
    }

    // [9] Remove staff
    @When("a staff no longer works here")
    public void aStaffNoLongerWorksHere() {

    }

    @Then("I should be able to remove them from my system")
    public void iShouldBeAbleToRemoveThemFromMySystem() {
        s = new Staff();
        d.add(s);
        assertTrue((d.staff).contains(s));
        d.remove(s);
        assertFalse((d.staff).contains(s));
    }

    // [9] Add staff
    @When("I employ a staff")
    public void iEmployAStaff() {
        s = new Staff();
    }

    @Then("I should add them to my system, such that I can easily look them up")
    public void iShouldAddThemToMySystemSuchThatICanEasilyLookThemUp() {
        d.add(s);
        assertTrue((d.staff).contains(s));
    }

    // [10] Assign to bed
    @When("receiving a patient")
    public void receivingAPatient() {
        p = new Patient();
    }

    @Then("I want to assign them to a specific bed, such that all patients are accounted for")
    public void iWantToAssignThemToASpecificBedSuchThatAllPatientsAreAccountedFor() {
        Bed bed1 = new Bed();
        d.assign(p,bed1);
        assertTrue(bed1.occupied());
    }

    // [11] Discharge patients
    @When("a patient's treatment is over")
    public void aPatientSTreatmentIsOver() {
        p = new Patient();
    }

    @Then("I should be able to discharge them, thus removing my responsibility")
    public void iShouldBeAbleToDischargeThemThusRemovingMyResponsibility() {
        d.add(p);
        assertTrue((d.patients).contains(p));
        d.remove(p);
        assertFalse((d.patients).contains(p));
    }

    // [11] Move patients
    @When("a patient needs to be moved to another department")
    public void aPatientNeedsToBeMovedToAnotherDepartment() {
        p = new Patient();
    }

    @Then("I should be able to remove the patient from my system")
    public void iShouldBeAbleToRemoveThePatientFromMySystem() {
        d.add(p);
        assertTrue((d.patients).contains(p));
        d.remove(p);
        assertFalse((d.patients).contains(p));
    }

    // [12] Move patient between beds
    @When("a patient needs to be relocated")
    public void aPatientNeedsToBeRelocated() {
        p = new Patient();
        b = new Bed();
        d.assign(p,b);

    }

    @Then("I should be able to move them between beds")
    public void iShouldBeAbleToMoveThemBetweenBeds() {
        assertTrue(b.patient().equals(p));
        Bed b2 = new Bed();
        d.move(p,b2);
        assertTrue(b2.patient().equals(p));
        assertFalse(b.patient().equals(p));
    }
}

