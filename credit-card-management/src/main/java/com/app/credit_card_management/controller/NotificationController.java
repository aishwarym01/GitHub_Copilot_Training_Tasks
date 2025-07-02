package com.app.credit_card_management.controller;

import com.app.credit_card_management.serviceimplementation.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/test")
    public ResponseEntity<String> sendTestEmail(@RequestParam String to) {
        emailService.sendEmail(to, "Test Email", "This is a test notification from Credit Card App.");
        return ResponseEntity.ok("Test email sent");
    }
}
