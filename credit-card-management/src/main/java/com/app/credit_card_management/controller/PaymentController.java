package com.app.credit_card_management.controller;

import com.app.credit_card_management.dto.PaymentDTO;
import com.app.credit_card_management.serviceinterfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> makePayment(@RequestBody PaymentDTO paymentDTO, Principal principal) {
        paymentService.makePayment(principal.getName(), paymentDTO);
        return ResponseEntity.ok("Payment successful");
    }
}
