package pl.thewalkingcode.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User extends BaseEntity implements Serializable {

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String salt;

    @Column
    private String name;

    @Column
    private String surname;

    @ManyToOne
    private Role role;

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
