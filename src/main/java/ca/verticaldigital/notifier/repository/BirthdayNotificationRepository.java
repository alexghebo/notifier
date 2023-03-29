package ca.verticaldigital.notifier.repository;

import ca.verticaldigital.notifier.notification.BirthdayNotification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Qualifier("birthdaynotification")
@Component
public interface BirthdayNotificationRepository extends JpaRepository<BirthdayNotification, Long> {

    Optional<BirthdayNotification> findById(Long personId);


}
