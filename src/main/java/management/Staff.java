package management;

public class Staff extends Person {
    private String email;
    private String initials;
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

    public String getInitials() {
        return initials;
    }
}
