package ca.verticaldigital.notifier.notification;

import ca.verticaldigital.notifier.entity.Person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class BirthdayNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person")
    @JoinColumn (name = "id")
    private Person person;

    @Column
    private LocalDateTime createdDate;

    @Column
    private boolean emailStatus;


}

