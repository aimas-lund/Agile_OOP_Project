package management_test;

import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import management.*;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

public class Clerk_test extends Clerk {

    private Clerk c1 = new Clerk();
    private Patient pat1 = new Patient();
    private Department dep1 = new Department();
    private Staff staff1 = new Staff();

    @Test
    private void setPersonNameTest(){
        c1.setPersonName(pat1,"Billy");
        assertEquals(pat1.getName(),"Billy");
    }
    @Test
    private void  setPersonSurnameTest(){
        c1.setPersonSurname(pat1,"Boy");
        assertEquals(pat1.getSurname(),"Boy");
    }
    @Test
    private void setPersonBirthdateTest(){
        c1.setPersonBirthdate(pat1,new Date(1998,12,28));
        assertEquals(pat1.getBirthdate(),new Date(1998,12,28));
    }
    @Test
    private void setPersonGenderTest(){
        c1.setPersonGender(pat1,1);
        assertEquals(pat1.getGender(),1);
    }
    @Test
    private void setPersonHomeAddressTest(){
        c1.setPersonHomeAddress(pat1,"7 Rue Chalgrin");
        assertEquals(pat1.getHomeAddress(),"7 Rue Chalgrin");
    }
    @Test
    private void registerPersonTest(){
        assertTrue(c1.registerPerson(pat1,dep1));
    }

    @Test
    private void addUniqueIdToPersonTest(){
        c1.addUniqueIdToPerson(staff1);
        assertNotNull(staff1.getUniqueId());
    }
    @Test
    private void setPersonInformationTest(){
        c1.setPersonInformation(pat1,"Billy","Boy",new Date(1998,12,28),1,"7 Rue Chalgrin",22901220);
        assertEquals(pat1.getName(),"Billy");
        assertEquals(pat1.getSurname(),"Boy");
        assertEquals(pat1.getBirthdate(),new Date(1998,12,28));
        assertEquals(pat1.getHomeAddress(),"7 Rue Chalgrin");
        assertEquals(pat1.getGender(),1);
        assertEquals(pat1.getPhoneNumber(),22901220);
    }
    @Test
    private void setPersonPhoneNumberTest(){
        assertFalse(c1.setPersonPhoneNumber(pat1,1919119199));
        assertTrue(c1.setPersonPhoneNumber(pat1,91818283));
    }
    @Test
    private void checkPatientRegistrationStatusTest(){
        assertFalse(c1.checkPatientRegistrationStatus(pat1,dep1));
        assertTrue(c1.registerPerson(pat1,dep1));
    }

}
