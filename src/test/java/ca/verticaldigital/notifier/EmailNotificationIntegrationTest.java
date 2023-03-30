package ca.verticaldigital.notifier;

import ca.verticaldigital.notifier.entity.Person;
import ca.verticaldigital.notifier.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailNotificationIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private EmailService emailService;

    @Test
    public void testSendBirthdayNotifications() throws Exception {
        LocalDate today = LocalDate.now();
        Person testPerson = new Person("John", "Doe", "johndoe@example.com", today, "Sibiu", false);
        testPerson = restTemplate.postForObject("/person", testPerson, Person.class);

        // Call the endpoint that triggers the email notification job
        restTemplate.postForObject("/birthday-notifications", null, Void.class);

        // Verify that the email service was called with the correct parameters
        verify(emailService, times(1)).sendBirthdayNotification(eq(testPerson.getEmail()), anyString(), anyString());
    }
}