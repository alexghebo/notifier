package ca.verticaldigital.notifier.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
@Table
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column
    private long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private Date birthdate;

    @Column
    private String city;

    @Column
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
