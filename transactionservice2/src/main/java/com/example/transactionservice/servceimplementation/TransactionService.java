//package com.example.transactionservice.servceimplementation;
//
//import java.util.Date;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.example.transactionservice.entity.Customer;
//import com.example.transactionservice.entity.Transaction;
//import com.example.transactionservice.entity.TransactionHistory;
//import com.example.transactionservice.exception.CustomerNotFoundException;
//import com.example.transactionservice.exception.InsufficientBalanceException;
//import com.example.transactionservice.repository.CustomerRepository;
//import com.example.transactionservice.repository.TransactionHistoryRepository;
//import com.example.transactionservice.repository.TransactionRepository;
//import com.example.transactionservice.serviceinterfaces.ITransactionService;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class TransactionService implements ITransactionService {
//
//    private final CustomerRepository customerRepository;
//    private final TransactionRepository transactionRepository;
//    private final TransactionHistoryRepository transactionHistoryRepository;
//
//    @Transactional
//    @Override
//    public void credit(Long customerId, Double amount, String modeOfTransaction) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
//
//        customer.setAccountBalance(customer.getAccountBalance() + amount);
//        customerRepository.save(customer);
//
//        Transaction transaction = new Transaction(null, customerId, new Date(), amount, "Credit", modeOfTransaction);
//        transactionRepository.save(transaction);
//
//        TransactionHistory history = new TransactionHistory(null, transaction.getTransactionId(), customerId, amount, "Credit", modeOfTransaction, new Date());
//        transactionHistoryRepository.save(history);
//    }
//
//    @Transactional
//    @Override
//    public void debit(Long customerId, Double amount, String modeOfTransaction) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
//
//        if (customer.getAccountBalance() < amount) {
//            throw new InsufficientBalanceException("Insufficient balance");
//        }
//
//        customer.setAccountBalance(customer.getAccountBalance() - amount);
//        customerRepository.save(customer);
//
//        Transaction transaction = new Transaction(null, customerId, new Date(), amount, "Debit", modeOfTransaction);
//        transactionRepository.save(transaction);
//
//        TransactionHistory history = new TransactionHistory(null, transaction.getTransactionId(), customerId, amount, "Debit", modeOfTransaction, new Date());
//        transactionHistoryRepository.save(history);
//    }
//
//    @Override
//    public Double getBalance(Long customerId) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
//        return customer.getAccountBalance();
//    }
//
//    @Override
//    public List<TransactionHistory> getTransactionHistory(Long customerId) {
//        return transactionHistoryRepository.findByCustomerId(customerId);
//    }
//}
//



package com.example.transactionservice.servceimplementation;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.transactionservice.entity.Customer;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.TransactionHistory;
import com.example.transactionservice.exception.CustomerNotFoundException;
import com.example.transactionservice.exception.InsufficientBalanceException;
import com.example.transactionservice.repository.CustomerRepository;
import com.example.transactionservice.repository.TransactionHistoryRepository;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.serviceinterfaces.ITransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    @Override
    public void credit(Long customerId, Double amount, String modeOfTransaction) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        customer.setAccountBalance(customer.getAccountBalance() + amount);
        customerRepository.save(customer);

        Transaction transaction = new Transaction(null, customerId, new Date(), amount, "Credit", modeOfTransaction);
        transactionRepository.save(transaction);

        TransactionHistory history = new TransactionHistory(null, transaction.getTransactionId(), customerId, amount, "Credit", modeOfTransaction, new Date());
        transactionHistoryRepository.save(history);
    }

    @Transactional
    @Override
    public void debit(Long customerId, Double amount, String modeOfTransaction) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        if (customer.getAccountBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        customer.setAccountBalance(customer.getAccountBalance() - amount);
        customerRepository.save(customer);

        Transaction transaction = new Transaction(null, customerId, new Date(), amount, "Debit", modeOfTransaction);
        transactionRepository.save(transaction);

        TransactionHistory history = new TransactionHistory(null, transaction.getTransactionId(), customerId, amount, "Debit", modeOfTransaction, new Date());
        transactionHistoryRepository.save(history);
    }

    @Override
    public Double getBalance(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customer.getAccountBalance();
    }

    @Override
    public List<TransactionHistory> getTransactionHistory(Long customerId) {
        return transactionHistoryRepository.findByCustomerId(customerId);
    }

    // New method to calculate compound interest
    @Override
    public double calculateCompoundInterest(double principal, double rate, int time) {
        double amount = principal * Math.pow((1 + rate / 100), time);
        double interest = amount - principal;
        return interest;
    }

    // Example method to apply interest calculation
    @Transactional
    @Override
    public void applyInterest(Long customerId, double rate, int time) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        double interest = calculateCompoundInterest(customer.getAccountBalance(), rate, time);
        customer.setAccountBalance(customer.getAccountBalance() + interest);
        customerRepository.save(customer);

        Transaction transaction = new Transaction(null, customerId, new Date(), interest, "Interest", "System");
        transactionRepository.save(transaction);

        TransactionHistory history = new TransactionHistory(null, transaction.getTransactionId(), customerId, interest, "Interest", "System", new Date());
        transactionHistoryRepository.save(history);
    }
}

