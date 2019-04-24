package management;

import exceptions.FormatException;

import java.util.Date;

public class Staff extends Person {
    private String email;
    private String initials;

    public Staff(){

    }
    public Staff(String name, String surname, Date birthdate, int gender, String homeaddress, int phonenumber, String email, String initials) {
        this.setName(name);
        this.setSurname(surname);
        this.setBirthdate(birthdate);
        this.setGender(gender);
        this.setHomeAddress(homeaddress);
        try {
            this.setPhoneNumber(phonenumber);
        } catch (FormatException e) {
            e.printStackTrace();
        }
        this.setEmail (email);
        this.setInitials (initials);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String[] getPersonInformation() {
        return new String[]{String.format("%d", this.getUniqueId()), this.getName(), this.getSurname(),
                this.getBirthdate().toString(), String.format("%d", this.getGender()), this.getHomeAddress(),
                String.format("%d", this.getPhoneNumber()), this.getEmail(), this.getInitials()};
    }

    private void setInitials(String i){
        this.initials = i;
    }
    public String getInitials() {
        return initials;
    }
}
