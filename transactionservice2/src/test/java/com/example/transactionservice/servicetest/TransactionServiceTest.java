package com.example.transactionservice.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.transactionservice.entity.Customer;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.TransactionHistory;
import com.example.transactionservice.exception.InsufficientBalanceException;
import com.example.transactionservice.repository.CustomerRepository;
import com.example.transactionservice.repository.TransactionHistoryRepository;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.servceimplementation.TransactionService;

class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCredit() {
        Long customerId = 1L;
        Double amount = 500.0;
        String modeOfTransaction = "Online";

        Customer customer = new Customer(customerId, "John Doe", "john.doe@example.com", "1234567890", "123 Main St", 1000.0);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        transactionService.credit(customerId, amount, modeOfTransaction);

        assertEquals(1500.0, customer.getAccountBalance());
        verify(customerRepository, times(1)).save(customer);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(transactionHistoryRepository, times(1)).save(any(TransactionHistory.class));
    }

    @Test
    void testDebit() {
        Long customerId = 1L;
        Double amount = 500.0;
        String modeOfTransaction = "Online";

        Customer customer = new Customer(customerId, "John Doe", "john.doe@example.com", "1234567890", "123 Main St", 1000.0);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        transactionService.debit(customerId, amount, modeOfTransaction);

        assertEquals(500.0, customer.getAccountBalance());
        verify(customerRepository, times(1)).save(customer);
        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(transactionHistoryRepository, times(1)).save(any(TransactionHistory.class));
    }

    @Test
    void testDebit_InsufficientBalance() {
        Long customerId = 1L;
        Double amount = 1500.0;
        String modeOfTransaction = "Online";

        Customer customer = new Customer(customerId, "John Doe", "john.doe@example.com", "1234567890", "123 Main St", 1000.0);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        assertThrows(InsufficientBalanceException.class, () -> transactionService.debit(customerId, amount, modeOfTransaction));
        verify(customerRepository, never()).save(any(Customer.class));
        verify(transactionRepository, never()).save(any(Transaction.class));
        verify(transactionHistoryRepository, never()).save(any(TransactionHistory.class));
    }

    @Test
    void testGetBalance() {
        Long customerId = 1L;

        Customer customer = new Customer(customerId, "John Doe", "john.doe@example.com", "1234567890", "123 Main St", 1000.0);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Double balance = transactionService.getBalance(customerId);

        assertEquals(1000.0, balance);
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    void testGetTransactionHistory() {
        Long customerId = 1L;

        TransactionHistory history1 = new TransactionHistory(1L, 1L, customerId, 500.0, "Credit", "Online", new Date());
        TransactionHistory history2 = new TransactionHistory(2L, 2L, customerId, 300.0, "Debit", "Online", new Date());
        when(transactionHistoryRepository.findByCustomerId(customerId)).thenReturn(Arrays.asList(history1, history2));

        var transactionHistory = transactionService.getTransactionHistory(customerId);

        assertEquals(2, transactionHistory.size());
        verify(transactionHistoryRepository, times(1)).findByCustomerId(customerId);
    }
}

