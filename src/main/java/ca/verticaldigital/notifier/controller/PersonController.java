package ca.verticaldigital.notifier.controller;

import ca.verticaldigital.notifier.entity.Person;
import ca.verticaldigital.notifier.service.PersonService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.softDeletePerson(id);
    }

    @PostMapping("/createPersons")
    public void createMultiplePersons(@RequestBody String jsonBody) throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        List<Person> personsList = objectMapper.readValue(jsonBody, new TypeReference<List<Person>>() {
        });

        personsList.forEach(person -> System.out.println(person.toString()));

        personsList.forEach(person -> personService.createPerson(person));
    }

    @GetMapping("/birthdays")
    public List<Person> PersonsWithBirthdays() {

    return personService.getBirthdays();

    }
}