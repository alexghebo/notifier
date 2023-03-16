package ca.verticaldigital.notifier.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
@Getter @Setter
public class ConfigProperties {

    private String emailSubject;

    private String toAddress;

    private String mailBody;

}
