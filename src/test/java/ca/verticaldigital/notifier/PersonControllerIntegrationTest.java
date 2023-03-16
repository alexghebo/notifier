package ca.verticaldigital.notifier;

import ca.verticaldigital.notifier.entity.Person;
import ca.verticaldigital.notifier.repository.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.Assertions.assertThat;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PersonControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private Person person1;
    private Person person2;

    @BeforeEach
    void setUp() {
        person1 = new Person();
        person1.setId(593819);
        person1.setFirstName("J");
        person1.setLastName("Carol");
        person1.setEmail("jcarol@example.com");
        person1.setBirthdate(LocalDate.of(1998, 02, 02));
        person1.setCity("Sibiu");
        person1.setDeleted(false);

        person2 = new Person();
        person2.setId(123456);
        person2.setFirstName("L");
        person2.setLastName("Popescu");
        person2.setEmail("lpopescu@example.com");
        person2.setBirthdate(LocalDate.of(1992, 02, 02));
        person2.setCity("Brasov");
        person2.setDeleted(false);

        person1 = personRepository.save(person1);
        person2 = personRepository.save(person2);
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    void testGetPersonById() throws Exception {
        mockMvc.perform(get("/person/{id}", person1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(person1.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person1.getLastName()))
                .andExpect(jsonPath("$.email").value(person1.getEmail()))
                .andExpect(jsonPath("$.birthdate").value(person1.getBirthdate().toString()))
                .andExpect(jsonPath("$.city").value(person1.getCity()));
    }

    @Test
    @DateTimeFormat
    void testGetAllPersons() throws Exception {
        mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(person1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(person1.getLastName()))
                .andExpect(jsonPath("$[0].email").value(person1.getEmail()))
                .andExpect(jsonPath("$[0].birthdate").value(person1.getBirthdate().toString()))
                .andExpect(jsonPath("$[0].city").value(person1.getCity()))
                .andExpect(jsonPath("$[1].firstName").value(person2.getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(person2.getLastName()))
                .andExpect(jsonPath("$[1].email").value(person2.getEmail()))
                .andExpect(jsonPath("$[1].birthdate").value(person2.getBirthdate().toString()))
                .andExpect(jsonPath("$[1].city").value(person2.getCity()));
    }

    @Test
    void testUpdatePerson() throws Exception {
        person1.setFirstName("Updated First Name");
        person1.setLastName("Updated Last Name");
        person1.setEmail("updatedemail@example.com");
        person1.setBirthdate(LocalDate.of(1998, 02, 02));
        person1.setCity("Updated City");

        mockMvc.perform(put("/person/{id}", person1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(person1.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person1.getLastName()))
                .andExpect(jsonPath("$.email").value(person1.getEmail()))
                .andExpect(jsonPath("$.birthdate").value(person1.getBirthdate().toString()))
                .andExpect(jsonPath("$.city").value(person1.getCity()));
    }

    @Test
    public void PostCreatePerson() throws Exception {
        Person person3 =
                new Person("Robert", "Andrei", "robert.andrei@vd.ro", LocalDate.of(2022, 02, 02), "Oradea", false);
        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(person3)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstName").value(person3.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(person3.getLastName()))
                .andExpect(jsonPath("$.email").value(person3.getEmail()))
                .andExpect(jsonPath("$.birthdate").value(person3.getBirthdate().toString()))
                .andExpect(jsonPath("$.city").value(person3.getCity()));

    }

    @Test
    public void DeletePerson() throws Exception {

        //test1
        Person person3;
        person3 = new Person();
        person3.setFirstName("J");
        person3.setLastName("Carol");
        person3.setEmail("jcarol@example.com");
        person3.setBirthdate(LocalDate.of(1998, 02, 02));
        person3.setCity("Sibiu");
        person3.setDeleted(false);
        personRepository.save(person3);

        mockMvc.perform(delete("/person/{id}", person3.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Optional<Person> deletedPerson = personRepository.findById(person3.getId());

        assert(deletedPerson.isPresent());
        assert(deletedPerson.get().isDeleted());

        //test2
        mockMvc.perform(delete("/person/{id}", person1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Optional<Person> deletedPersonAnother = personRepository.findById(person1.getId());

        assert(deletedPersonAnother.isPresent());
        assert(deletedPersonAnother.get().isDeleted());

    }

    @Test
    void testCreatePerson() throws Exception {
        Person newPerson = new Person();
        newPerson.setFirstName("New");
        newPerson.setLastName("Person");
        person1.setEmail("new@example.com");
        person1.setBirthdate(LocalDate.of(1998, 02, 02));
        person1.setCity("Bucuresti");
        person1.setDeleted(false);
    }

    public void createPersons() throws Exception {
        List<Person> persons = Arrays.asList(
                new Person("John", "Doe", "john.doe@example.com", LocalDate.of(1990, 1, 1), "New York", false),
                new Person("Jane", "Doe", "jane.doe@example.com", LocalDate.of(1992, 2, 2), "Los Angeles", false),
                new Person("Bob", "Smith", "bob.smith@example.com", LocalDate.of(1985, 3, 3), "Chicago", false)
        );

        mockMvc.perform(post("/createPersons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(persons)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(persons.size()))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"))
                .andExpect(jsonPath("$[2].firstName").value("Bob"))
                .andExpect(jsonPath("$[2].lastName").value("Smith"));

        // Verify that the persons were saved to the database
        List<Person> savedPersons = personRepository.findAll();
        assertThat(savedPersons.size()).isEqualTo(persons.size());
        assertThat(savedPersons.get(0).getFirstName()).isEqualTo("John");
        assertThat(savedPersons.get(1).getFirstName()).isEqualTo("Jane");
        assertThat(savedPersons.get(2).getFirstName()).isEqualTo("Bob");
    }

    @Test
    public void getBirthdays() throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysFromNow = today.plusDays(30);

        Person person1 = new Person("John", "D", "johnd@example.com", LocalDate.of(1990, 5, 15), "Suceava", false);
        Person person2 = new Person("Lara", "Dan", "laradan@example.com", LocalDate.of(1992, 8, 23), "Ploiesti", false);
        Person person3 = new Person("Andrei", "Popescu", "andreipopescu@example.com", thirtyDaysFromNow, "Suceava", false);

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);

        mockMvc.perform(get("/birthdays"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(person3.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(person3.getLastName()))
                .andExpect(jsonPath("$[0].email").value(person3.getEmail()))
                .andExpect(jsonPath("$[0].birthdate").value(person3.getBirthdate().toString()))
                .andExpect(jsonPath("$[0].city").value(person3.getCity()));
    }


}
