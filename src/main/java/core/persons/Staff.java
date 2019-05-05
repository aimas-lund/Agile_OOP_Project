package core.persons;

import java.util.Date;

public class Staff extends Person {
    private String email;
    private String initials;

    public Staff(String uniqueId) {
        super(uniqueId);
    }

    public Staff(){
    }

    public Staff(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
    }

    public Staff(String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber, String email, String initials) {
        super(name, surname, birthdate, gender, homeaddress, phonenumber);
        this.setEmail(email);
        this.setInitials(initials);
    }

    public Staff(String uniqueId, String name, String surname, Date birthdate, Gender gender, String homeaddress, int phonenumber, String email, String initials) {
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
                dateToString(this.getBirthdate()), getGender().toString(), this.getHomeAddress(),
                String.format("%d", this.getPhoneNumber()), this.email, this.initials};
    }

    public String getEmail() {
        return email;
    }

    boolean setEmail(String email) {
        if (!(email.contains("@agile_hospital.com"))) {
            return false;
        }
        String initials = email.substring(0, 4).toUpperCase();
        this.initials = initials;
        this.email = initials + "@agile_hospital.com";
        return true;
    }

    void setInitials(String i) {
        this.initials = i.toUpperCase();
        this.email = initials + "@agile_hospital.com";
    }

    public String getInitials() {
        return initials;
    }
}
