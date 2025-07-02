package com.app.credit_card_management.serviceimplementation;
import com.app.credit_card_management.entity.CreditCard;
import com.app.credit_card_management.entity.Transaction;
import com.app.credit_card_management.entity.User;
import com.app.credit_card_management.repository.CreditCardRepository;
import com.app.credit_card_management.repository.TransactionRepository;
import com.app.credit_card_management.repository.UserRepository;
import com.app.credit_card_management.serviceinterfaces.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

     
// Make a transaction using a credit card ID.
    @Override
    public Transaction makeTransaction(Transaction transaction, Long cardId) {
        CreditCard card = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        BigDecimal newBalance = card.getBalance().add(transaction.getAmount());

        if (newBalance.compareTo(card.getCreditLimit()) > 0) {
            throw new RuntimeException("Credit limit exceeded");
        }

        card.setBalance(newBalance);
        transaction.setCreditCard(card);
        transaction.setTransactionDate(LocalDateTime.now());

        creditCardRepository.save(card);
        return transactionRepository.save(transaction);
    }


// Record a transaction for a user (not tied to a specific card).
    
    @Override
    public void recordTransaction(String username, BigDecimal amount, String description) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setUser(user);

        transactionRepository.save(transaction);

        // Send alert if amount exceeds threshold
        if (amount.compareTo(new BigDecimal("10000")) > 0) {
            emailService.sendEmail(
                    user.getEmail(),
                    "Large Transaction Alert",
                    "A transaction of ₹" + amount + " was recorded on your account. If this wasn't you, please contact support."
            );
        }
    }

    
// Get all transactions for a specific credit card.
    @Override
    public List<Transaction> getTransactionsByCardId(Long cardId) {
        CreditCard card = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        return transactionRepository.findByCreditCard(card);
    }

    
// Get all transactions in the system.
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
}
