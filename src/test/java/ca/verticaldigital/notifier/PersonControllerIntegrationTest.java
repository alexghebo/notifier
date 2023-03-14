package ca.verticaldigital.notifier;

import ca.verticaldigital.notifier.entity.Person;
import ca.verticaldigital.notifier.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Date;

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
    public void POSTCreatePerson() throws Exception {
        Person person3 =
                new Person("eu", "eu", "eu", LocalDate.of(2022, 02, 02), "eu", false);
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
    void testCreatePerson() throws Exception {
        Person newPerson = new Person();
        newPerson.setFirstName("New");
        newPerson.setLastName("Person");
        person1.setEmail("new@example.com");
        person1.setBirthdate(LocalDate.of(1998, 02, 02));
        person1.setCity("Bucuresti");
        person1.setDeleted(false);
    }

}
