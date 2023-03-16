package ca.verticaldigital.notifier.repository;

import ca.verticaldigital.notifier.notification.BirthdayNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BirthdayNotificationRepository extends JpaRepository<BirthdayNotification, Long> {


        List<BirthdayNotification> findBySentFalse();

        List<BirthdayNotification> findByPersonId(Long personId);

        // Add any other custom query methods as needed

}


