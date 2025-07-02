package com.app.credit_card_management.controller;

import com.app.credit_card_management.entity.Transaction;
import com.app.credit_card_management.entity.User;
import com.app.credit_card_management.serviceinterfaces.ITransactionService;
import com.app.credit_card_management.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/users")    
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/transactions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}
