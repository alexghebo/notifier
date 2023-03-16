package ca.verticaldigital.notifier.notification;

import ca.verticaldigital.notifier.entity.Person;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class BirthdayNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "email_status")
    private boolean emailStatus;

    public BirthdayNotification(){
        this.person = person;
        this.createdDate = createdDate;
        this.emailStatus = emailStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setEmailStatus(boolean emailStatus) {
        this.emailStatus = emailStatus;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public boolean getEmailStatus() {
        return emailStatus;
    }
}

