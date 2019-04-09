package management;

import java.util.Date;

interface IChangeInformation {
    void setPersonInformation(Person person, String name, String surname, Date birthdate, int gender, String homeAddress, int phoneNumber);

    void setPersonPhoneNumber(Person person, int phoneNumber);

    void setPersonHomeAddress(Person person, String homeAddress);

    void setPersonGender(Person person, int gender);

    void setPersonBirthdate(Person person, Date birthdate);

    void setPersonSurname(Person person, String surname);

    void setPersonName(Person person, String name);

}
