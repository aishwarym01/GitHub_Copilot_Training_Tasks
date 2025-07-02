package com.app.credit_card_management.controller;
import com.app.credit_card_management.entity.Transaction;
import com.app.credit_card_management.serviceinterfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> makeTransaction(@RequestBody Transaction transaction,
                                                       @RequestParam Long cardId) {
        Transaction saved = transactionService.makeTransaction(transaction, cardId);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/cards/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByCard(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionsByCardId(id));
    }
}
