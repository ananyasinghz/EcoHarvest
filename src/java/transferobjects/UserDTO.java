package transferobjects;

/**
 * This class is to create a data transfer object for the user table in the database
 */
public class UserDTO {
    private int id;
    private String name;
    private String password;
    private boolean subscribeToPhone;
    private boolean subscribeToEmail;
    private String location;
    private String role;
    private String phone;
    private String mail;
    private String preference;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isSubscribeToPhone() {
        return subscribeToPhone;
    }
    public void setSubscribeToPhone(boolean subscribeToPhone) {
        this.subscribeToPhone = subscribeToPhone;
    }

    public boolean isSubscribeToEmail() {
        return subscribeToEmail;
    }
    public void setSubscribeToEmail(boolean subscribeToEmail) {
        this.subscribeToEmail = subscribeToEmail;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPreference() {
        return preference;
    }
    public void setPreference(String preference) {
        this.preference = preference;
    }

    public boolean getSubscribeToPhone() {
        return subscribeToPhone;
    }

    public boolean getSubscribeToEmail() {
        return subscribeToEmail;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", subscribeToPhone=" + subscribeToPhone +
                ", subscribeToEmail=" + subscribeToEmail +
                ", location='" + location + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", preference='" + preference + '\'' +
                '}';
    }

}
