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
    public void sendBirthdayNotifications() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.plusDays(1);
        LocalDate endDate = today.plusDays(30);
        List<Person> persons = personRepository.findAll();
        //List<Person> persons = personRepository.findAll();
//        persons.stream()
//                .filter(person -> person.getBirthdate().getDayOfMonth() == today.getDayOfMonth() && person.getBirthdate().getMonth() == today.getMonth())
//                .forEach(person -> {
//                    BirthdayNotification notification = new BirthdayNotification();
//                    notification.setCreatedDate(LocalDateTime.now());
//                    notification.setPerson(person);
//                    notification.setEmailStatus(false);
//                    notificationRepository.save(notification);
//                });
        for (Person person : persons) {
            String recipient = person.getEmail();
            String subject = "Happy Birthday!";
            String body = "Dear " + person.getFirstName() + ",\n\n"
                    + "Happy Birthday from us!\n\n"
                    + "Have a great day,\n"
                    + "Vertical Digital Team";
            emailService.sendBirthdayNotification(recipient, subject, body);
        }
        System.out.println("Birthday Notification Job ran at " + LocalDateTime.now());

    }
}
