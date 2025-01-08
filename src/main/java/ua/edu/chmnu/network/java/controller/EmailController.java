package ua.edu.chmnu.network.java.controller;

import ua.edu.chmnu.network.java.service.EmailReceiverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailReceiverService emailReceiverService;

    public EmailController(EmailReceiverService emailReceiverService) {
        this.emailReceiverService = emailReceiverService;
    }

    @GetMapping("/check-email")
    public String checkEmail() {
        emailReceiverService.checkInbox();
        return "Checking email inbox!";
    }
}