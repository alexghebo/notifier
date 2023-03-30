package ca.verticaldigital.notifier.notification;

import ca.verticaldigital.notifier.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name="Birthdays")
public class BirthdayNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    @JoinColumn (name = "id")
    private Person person;

    @Column
    private LocalDateTime createdDate;

    @Column
    private boolean emailStatus;


}