package ca.verticaldigital.notifier.controller;

import ca.verticaldigital.notifier.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("SlackController")
@RequestMapping("/apps")
public class SlackController {
    @Autowired
    private AppService appService;

    @PostMapping("/{message}")
    public ResponseEntity<String> sendSimpleMessageToSlack(
            @PathVariable(name = "message") String message){
        appService.sendMessageToSlack(message);
        return ResponseEntity.ok(message);
    }
}
