package ca.verticaldigital.notifier.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Getter @Setter @NoArgsConstructor
public class MailConfig {

    public MailConfig(String emailSubject, String toAddress, String mailBody) {
        this.emailSubject = emailSubject;
        this.toAddress = toAddress;
        this.mailBody = mailBody;
    }

    private String emailSubject;

    private String toAddress;

    private String mailBody;

}
