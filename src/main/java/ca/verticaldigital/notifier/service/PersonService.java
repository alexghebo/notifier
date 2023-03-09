package ca.verticaldigital.notifier.service;

import ca.verticaldigital.notifier.entity.Person;

import java.util.List;

public class PersonService {
    public Person getPersonById(Long id) {
        Person[] personList = new Person[0];
        for(Person p : personList) {
            if(p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Person> getAllPersons() {
        return null;
    }

    public void updatePerson(Long id, Person updatedPerson) {
    }

    public void createPerson(Person newPerson) {
    }

    public void deletePerson(Long id) {
    }

}