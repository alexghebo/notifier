package ca.verticaldigital.notifier.repository;

import ca.verticaldigital.notifier.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByDeleted(boolean deleted);

    List<Person> findAllByBirthdateBetween(LocalDate currentDate, LocalDate beforeDate);

    List<Person> findByBirthdate(LocalDate birthdate);
}