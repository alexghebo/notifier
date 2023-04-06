package ca.verticaldigital.notifier;

import ca.verticaldigital.notifier.entity.Person;
import ca.verticaldigital.notifier.repository.PersonRepository;
import ca.verticaldigital.notifier.service.SlackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SlackServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SlackService slackService;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void sendBirthdayMessageToSlackChannel() throws Exception {
        // Person with a birthday today
        LocalDate now = LocalDate.now();
        Person person = new Person("Josh", "Lee", "joshlee@example.com", LocalDate.of(1980, now.getMonthValue(), now.getDayOfMonth()), "Sibiu", false);

        // Save Person to the database
        Person savedPerson = personRepository.save(person);

        // Call the Slack service
        slackService.sendMessage(String.valueOf(savedPerson));

        // Verify that the message was sent to the Slack channel
        mockMvc.perform(get("/slack/messages")
                        .param("channel", "general"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.channel").value("general"))
                .andExpect(jsonPath("$.messages[0].text").value("Happy Birthday, " + person.getFirstName() + "! 🎂🎉🎁"));
    }
}
