package ca.verticaldigital.notifier.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;


@Service
public class SlackService {

    @Value("${spring.slack.bot-token}")
    private String botToken;

    @Value("${spring.slack.channel}")
    private String channel;

    private WebClient webClient = WebClient.create();;

    @PostConstruct
    private void init() {
        webClient = WebClient.builder()
                .baseUrl("https://slack.com/api/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", "Bearer " + botToken)
                .build();
    }

    public void sendMessage(String message) {
        try {
            webClient.post()
                    .uri("chat.postMessage")
                    .body(BodyInserters.fromValue(Map.of(
                            "channel", channel,
                            "text", message
                    )))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            System.out.println("Error occurred while sending message.");
        }
    }
}

