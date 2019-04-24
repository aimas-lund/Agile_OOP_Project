import management.InformationGenerator;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDepartment.class,
        HospitalBedTest.class,
        ClerkTest.class,
        DaoPatientImplTest.class,
        DatabaseTest.class,
        InformationGeneratorTest.class,
        PatientTest.class
})

public class FullSuitTest {
}
