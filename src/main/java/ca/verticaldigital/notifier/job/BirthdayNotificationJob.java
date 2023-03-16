package ca.verticaldigital.notifier.job;

import ca.verticaldigital.notifier.entity.Person;
import ca.verticaldigital.notifier.notification.BirthdayNotification;
import ca.verticaldigital.notifier.repository.BirthdayNotificationRepository;
import ca.verticaldigital.notifier.repository.PersonRepository;
import ca.verticaldigital.notifier.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class BirthdayNotificationJob {

    private final PersonRepository personRepository;
    private final BirthdayNotificationRepository notificationRepository;
    private final EmailService emailService;

    @Autowired
    public BirthdayNotificationJob(PersonRepository personRepository, BirthdayNotificationRepository notificationRepository, EmailService emailService) {
        this.personRepository = personRepository;
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 8 * * *") // runs every day at 8:00 AM
    public void createBirthdayNotifications() {
        LocalDate today = LocalDate.now();
        List<Person> peopleWithBirthdayToday = personRepository.findByBirthdate(today);
        for (Person person : peopleWithBirthdayToday) {
            BirthdayNotification notification = new BirthdayNotification();
            notification.setPerson(person);
            notification.setCreatedDate(LocalDateTime.now());
            notification.setEmailStatus(false);
            notificationRepository.save(notification);
            emailService.sendBirthdayNotification(person.getEmail());
        }
    }
}
