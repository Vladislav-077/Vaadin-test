package pojo;

import lombok.Data;
import java.io.Serializable;


public @Data class Users implements Serializable {
    private static final long serialVersionUID = 5643002875138191294L;

    private String firstName;
    private String lastName;
    private String otchestvo;
    private String email;
    private String telephone;

    public Users(String firstName, String lastName, String otchestvo, String email, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otchestvo = otchestvo;
        this.email = email;
        this.telephone = telephone;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOtchestvo() {
        return otchestvo;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getFirstName() {
        return firstName;
    }
}