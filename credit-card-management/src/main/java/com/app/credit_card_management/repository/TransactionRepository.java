package com.app.credit_card_management.repository;

import com.app.credit_card_management.entity.CreditCard;
import com.app.credit_card_management.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCreditCard(CreditCard creditCard);
}
