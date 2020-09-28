package my.vaadin.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Users{

    private String firstName;
    private String lastName;
    private String otchestvo;
    private String email;
    private String telephone;
    private String pol;
    private Date birthday;

    public Users(String firstName, String lastName, String otchestvo, String email, String telephone,String pol, Date birthday ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otchestvo = otchestvo;
        this.email = email;
        this.telephone = telephone;
        this.pol = pol;
        this.birthday = birthday;
    }

}