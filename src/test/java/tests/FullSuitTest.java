package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.HospitalBedTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDepartment.class,
        HospitalBedTest.class
})

public class FullSuitTest {
    static {
        System.out.println("Something");
    }

}
