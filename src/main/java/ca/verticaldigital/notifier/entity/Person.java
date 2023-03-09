package ca.verticaldigital.notifier.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthdate;
    private String city;
    private boolean deleted;

    public Person(String firstName, String lastName, String email, Date birthdate, String city, boolean deleted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.city = city;
        this.deleted = deleted;
    }
}
