package ca.verticaldigital.notifier;

import ca.verticaldigital.notifier.configuration.MailConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.testng.Assert.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = MailConfig.class)
@TestPropertySource("classpath:mail-config-test.properties")
public class ConfigPropMailTests {

    @Autowired
    private MailConfig mailConfig;

    @Test
    void configPropMail() {

        assertEquals(mailConfig.getToAddress(), "lv@vd.com");
        assertEquals(mailConfig.getMailBody(), "today");
        assertEquals(mailConfig.getEmailSubject(), "birth");

    }
}
