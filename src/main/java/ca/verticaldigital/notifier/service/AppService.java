//package ca.verticaldigital.notifier.service;
//
//import com.github.seratch.jslack.Slack;
//import com.github.seratch.jslack.api.webhook.Payload;
//import com.github.seratch.jslack.api.webhook.WebhookResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class AppService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(AppService.class);
//    private static final String NEW_LINE = "\n";
//
//    @Value("${slack.webhook}")
//    private String urlSlackWebHook;
//
//    public void sendMessageToSlack(String message) {
//        StringBuilder messageBuilder = new StringBuilder();
//        messageBuilder.append("Happy Birthday: ").append(message).append(NEW_LINE);
//        messageBuilder.append("*Item example*: ").append(exampleMessage()).append(NEW_LINE);
//
//        process(messageBuilder.toString());
//    }
//
//    private void process(String message) {
//        Payload payload = Payload.builder()
//                .channel("test123")
//                .username("Oana Puscas")
//                .text(message)
//                .build();
//
//        try {
//            WebhookResponse webhookResponse = Slack.getInstance().send(urlSlackWebHook, payload);
//            LOGGER.info("code -> " + webhookResponse.getCode());
//            LOGGER.info("body -> " + webhookResponse.getBody());
//        } catch (IOException e) {
//            LOGGER.error("Unexpected Error! WebHook: " + urlSlackWebHook);
//        }
//    }
//
//    private String exampleMessage() {
//        return "Happy Birthday";
//    }
//}
