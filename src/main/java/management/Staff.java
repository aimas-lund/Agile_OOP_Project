package management;

import java.util.Date;

public class Staff extends Person {
    private String email;
    private String initials;

    public Staff(){
    }

    public Staff(String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
    }
    public Staff(String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber, String email, String initials) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
        this.setEmail(email);
        this.setInitials(initials);
    }

    public Staff(String uniqueId, String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber, String email, String initials) {
        super(uniqueId, name, surname, birthdate, gender, homeaddress, phonenumber);
        this.setEmail(email);
        this.setInitials(initials);
    }

    public Staff(String name, String surname) {
        super(name, surname);
    }

    @Override
    public String[] getPersonInformation() {
        return new String[]{this.getUniqueId(), this.getName(), this.getSurname(),
                dateToString(this.getBirthdate()), String.format("%d", this.getGender()), this.getHomeAddress(),
                String.format("%d", this.getPhoneNumber()), this.getEmail(), this.getInitials()};
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
        this.initials = email.substring(0, 4);
    }

    void setInitials(String i) {
        this.initials = i;
    }

    public String getInitials() {
        return initials;
    }
}
