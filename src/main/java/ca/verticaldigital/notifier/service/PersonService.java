package ca.verticaldigital.notifier.service;

import ca.verticaldigital.notifier.entity.Person;
import ca.verticaldigital.notifier.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person with id " + id + " not found"));
    }

    public List<Person> getAllPersons() {
        return personRepository.findAllByDeleted(false);
    }

    public Person updatePerson(Long id, Person person) {
        Person existingPerson = getPersonById(id);
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        existingPerson.setEmail(person.getEmail());
        existingPerson.setBirthdate(person.getBirthdate());
        existingPerson.setCity(person.getCity());
        return personRepository.save(existingPerson);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public void softDeletePerson(Long id) {
        Person existingPerson = getPersonById(id);
        existingPerson.setDeleted(true);
        personRepository.save(existingPerson);
    }
}

