package tests.core.persons;

import core.buildings.OutDepartment;
import core.persons.Doctor;
import core.persons.Gender;
import core.persons.Patient;
import core.persons.PersonInformationFacade;
import core.utility.Speciality;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DoctorTest {

    private Doctor doctor;
    private Date birthdate = new Date(631152000000L);
    private PersonInformationFacade doctorFacade;
    private OutDepartment waitingRoom;
    private Patient patient1;
    private Patient patient2;

    @Before
    public void setup() {
        doctor = new Doctor(Speciality.NEUROLOGY, "Dr.", "Pjuskebusk",
                birthdate, Gender.MALE,"Tardis",
                99999999);
        waitingRoom = new OutDepartment("TheUniquestOfIDs", "Waiting Room");
        patient1 = new Patient("Foo");
        patient2 = new Patient("Bar");
        doctorFacade = new PersonInformationFacade(doctor);
    }

    @After
    public void tearDown() {
        waitingRoom.remove(patient1);
        waitingRoom.remove(patient2);

    }

    @Test
    public void getSpecialtyTest() {
        assertEquals(doctor.getSpeciality(), Speciality.NEUROLOGY);
    }

    @Test
    public void getTreatedPatientTest() {
        waitingRoom.addWaitingPatient(patient1);
        doctor.callWaitingPatient(waitingRoom);
        assertEquals(doctor.getTreatedPatient(), patient1);
    }

    @Test
    public void callWaitingPatientTest() {
        waitingRoom.addWaitingPatient(patient1);
        waitingRoom.addWaitingPatient(patient2);
        doctor.callWaitingPatient(waitingRoom);
        assertEquals(doctor.getTreatedPatient(), patient1);
        doctor.callWaitingPatient(waitingRoom);
        assertEquals(doctor.getTreatedPatient(), patient2);
    }

    @Test
    public void dischargeTreatedPatientTest() {
        waitingRoom.addWaitingPatient(patient1);
        doctor.callWaitingPatient(waitingRoom);
        assertEquals(doctor.getTreatedPatient(), patient1);
        doctor.dischargeTreatedPatient();
        assertNull(doctor.getTreatedPatient());
    }

    @Test
    public void getDoctorNameTest() {
        assertEquals(doctor.getName(), "Dr.");
        assertEquals(doctor.getSurname(), "Pjuskebusk");
        assertEquals(doctor.getHomeAddress(), "Tardis");
        assertEquals(doctor.getSpeciality(), Speciality.NEUROLOGY);
    }

    @Test
    public void doctorConstructorWithUUIDTest() {
        doctor = new Doctor("1011", "Dr.", "Pjuskebusk",
                birthdate, Gender.MALE,"Tardis",
                99999999, "FOBA@agile_hospital.com",
                "FOBA", Speciality.NEUROLOGY);
        assertEquals(doctor.getUniqueId(), "1011");
        assertEquals(doctor.getSpeciality(), Speciality.NEUROLOGY);
    }
}
