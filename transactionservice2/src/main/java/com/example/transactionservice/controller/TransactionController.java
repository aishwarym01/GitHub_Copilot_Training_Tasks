//package com.example.transactionservice.controller;
////
////import java.util.List;
////
////import org.springframework.http.HttpStatus;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.ExceptionHandler;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.bind.annotation.RequestBody;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.bind.annotation.RestController;
////
////import com.example.transactionservice.dto.TransactionRequest;
////import com.example.transactionservice.entity.TransactionHistory;
////import com.example.transactionservice.exception.CustomerNotFoundException;
////import com.example.transactionservice.serviceinterfaces.ITransactionService;
////
////import lombok.RequiredArgsConstructor;
////
////@RestController
////@RequestMapping("/api/transactions")
////@RequiredArgsConstructor
////public class TransactionController {
////
////    private final ITransactionService transactionService;
////
////    @PostMapping("/credit")
////    public ResponseEntity<String> credit(@RequestBody TransactionRequest request) {
////        transactionService.credit(request.getCustomerId(), request.getAmount(), request.getModeOfTransaction());
////        return ResponseEntity.ok("Amount credited successfully");
////    }
////
////    @PostMapping("/debit")
////    public ResponseEntity<String> debit(@RequestBody TransactionRequest request) {
////        transactionService.debit(request.getCustomerId(), request.getAmount(), request.getModeOfTransaction());
////        return ResponseEntity.ok("Amount debited successfully");
////    }
////
////    @GetMapping("/balance")  //For testing - transactions/balance?customerId=1
////    public ResponseEntity<Double> getBalance(@RequestParam Long customerId) {
////        return ResponseEntity.ok(transactionService.getBalance(customerId));
////    }
////
////    @GetMapping("/history")
////    public ResponseEntity<List<TransactionHistory>> getTransactionHistory(@RequestParam Long customerId) {
////        return ResponseEntity.ok(transactionService.getTransactionHistory(customerId));
////    }
////
////    @ExceptionHandler(CustomerNotFoundException.class)
////    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
////        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
////    }
////}
//
//
//
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.transactionservice.dto.TransactionRequest;
//import com.example.transactionservice.entity.TransactionHistory;
//import com.example.transactionservice.exception.CustomerNotFoundException;
//import com.example.transactionservice.serviceinterfaces.ITransactionService;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequestMapping("/api/transactions")
//@RequiredArgsConstructor
//public class TransactionController {
//
//    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
//
//    private final ITransactionService transactionService;
//
//    @PostMapping("/credit")
//    public ResponseEntity<String> credit(@RequestBody TransactionRequest request) {
//        logger.info("Received credit request for customer ID: {}", request.getCustomerId());
//        transactionService.credit(request.getCustomerId(), request.getAmount(), request.getModeOfTransaction());
//        logger.debug("Credit operation completed for customer ID: {}", request.getCustomerId());
//        return ResponseEntity.ok("Amount credited successfully");
//    }
//
//    @PostMapping("/debit")
//    public ResponseEntity<String> debit(@RequestBody TransactionRequest request) {
//        logger.info("Received debit request for customer ID: {}", request.getCustomerId());
//        transactionService.debit(request.getCustomerId(), request.getAmount(), request.getModeOfTransaction());
//        logger.debug("Debit operation completed for customer ID: {}", request.getCustomerId());
//        return ResponseEntity.ok("Amount debited successfully");
//    }
//
//    @GetMapping("/balance")
//    public ResponseEntity<Double> getBalance(@RequestParam Long customerId) {
//        logger.info("Fetching balance for customer ID: {}", customerId);
//        Double balance = transactionService.getBalance(customerId);
//        logger.debug("Balance fetched for customer ID: {}: {}", customerId, balance);
//        return ResponseEntity.ok(balance);
//    }
//
//    @GetMapping("/history")
//    public ResponseEntity<List<TransactionHistory>> getTransactionHistory(@RequestParam Long customerId) {
//        logger.info("Fetching transaction history for customer ID: {}", customerId);
//        List<TransactionHistory> history = transactionService.getTransactionHistory(customerId);
//        logger.debug("Transaction history fetched for customer ID: {}", customerId);
//        return ResponseEntity.ok(history);
//    }
//
//    @ExceptionHandler(CustomerNotFoundException.class)
//    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
//        logger.error("Customer not found: {}", ex.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
//}
//



package com.example.transactionservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactionservice.dto.TransactionRequest;
import com.example.transactionservice.entity.TransactionHistory;
import com.example.transactionservice.exception.CustomerNotFoundException;
import com.example.transactionservice.serviceinterfaces.ITransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final ITransactionService transactionService;

    @PostMapping("/credit")
    public ResponseEntity<String> credit(@RequestBody TransactionRequest request) {
        logger.info("Received credit request for customer ID: {}", request.getCustomerId());
        transactionService.credit(request.getCustomerId(), request.getAmount(), request.getModeOfTransaction());
        logger.debug("Credit operation completed for customer ID: {}", request.getCustomerId());
        return ResponseEntity.ok("Amount credited successfully");
    }

    @PostMapping("/debit")
    public ResponseEntity<String> debit(@RequestBody TransactionRequest request) {
        logger.info("Received debit request for customer ID: {}", request.getCustomerId());
        transactionService.debit(request.getCustomerId(), request.getAmount(), request.getModeOfTransaction());
        logger.debug("Debit operation completed for customer ID: {}", request.getCustomerId());
        return ResponseEntity.ok("Amount debited successfully");
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getBalance(@RequestParam Long customerId) {
        logger.info("Fetching balance for customer ID: {}", customerId);
        Double balance = transactionService.getBalance(customerId);
        logger.debug("Balance fetched for customer ID: {}: {}", customerId, balance);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/history")
    public ResponseEntity<List<TransactionHistory>> getTransactionHistory(@RequestParam Long customerId) {
        logger.info("Fetching transaction history for customer ID: {}", customerId);
        List<TransactionHistory> history = transactionService.getTransactionHistory(customerId);
        logger.debug("Transaction history fetched for customer ID: {}", customerId);
        return ResponseEntity.ok(history);
    }

    @PostMapping("/apply-interest")
    public ResponseEntity<String> applyInterest(@RequestParam Long customerId, @RequestParam double rate, @RequestParam int time) {
        logger.info("Applying interest for customer ID: {}", customerId);
        transactionService.applyInterest(customerId, rate, time);
        logger.debug("Interest applied for customer ID: {}", customerId);
        return ResponseEntity.ok("Interest applied successfully");
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        logger.error("Customer not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
