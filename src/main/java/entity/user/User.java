package entity.user;

public class User {
    
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;

    // data coupling
    public User(int id, String name, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public User cloneInformation() {
        return new User(this.id, this.name, this.email, this.address, this.phone);
    }
    
    // override toString method
    @Override
    public String toString() {
        return "{" +
            "  username='" + name + "'" +
            ", email='" + email + "'" +
            ", address='" + address + "'" +
            ", phone='" + phone + "'" +
            "}";
    }

    // getter and setter
    public String getName() {
        return this.name;
    }

    // data coupling
    public void setusername(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    // data coupling
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    // data coupling
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    // data coupling
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
