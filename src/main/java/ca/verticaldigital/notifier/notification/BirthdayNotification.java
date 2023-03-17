package ca.verticaldigital.notifier.notification;

import ca.verticaldigital.notifier.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class BirthdayNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Person person;

    @Column
    private LocalDateTime createdDate;

    @Column
    private boolean emailStatus;


}

