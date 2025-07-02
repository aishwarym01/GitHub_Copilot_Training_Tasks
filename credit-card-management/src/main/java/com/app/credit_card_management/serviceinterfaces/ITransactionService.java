package com.app.credit_card_management.serviceinterfaces;

import com.app.credit_card_management.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface ITransactionService {
    Transaction makeTransaction(Transaction transaction, Long cardId);
    List<Transaction> getTransactionsByCardId(Long cardId);
    List<Transaction> getAllTransactions();
	void recordTransaction(String username, BigDecimal amount, String description);
}
