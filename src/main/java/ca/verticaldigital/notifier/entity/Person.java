package ca.verticaldigital.notifier.entity;

import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private Date birthdate;
    private String city;
    private boolean deleted;
    private int id;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, Date birthdate, String city, boolean deleted, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.city = city;
        this.deleted = deleted;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

}













