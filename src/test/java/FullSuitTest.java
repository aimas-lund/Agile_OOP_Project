import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HospitalBedTest.class,
        ClerkTest.class,
        DaoPatientImplTest.class,
        DatabaseTest.class,
        InformationGeneratorTest.class,
        PatientTest.class,
        DepartmentTest.class
})

public class FullSuitTest {
}
