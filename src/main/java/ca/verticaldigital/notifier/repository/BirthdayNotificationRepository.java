package ca.verticaldigital.notifier.repository;

import ca.verticaldigital.notifier.notification.BirthdayNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirthdayNotificationRepository extends JpaRepository<BirthdayNotification, Long> {


        List<BirthdayNotification> findBySentFalse();

        List<BirthdayNotification> findByPersonId(Long personId);


}


