package com.app.credit_card_management.repository;

import com.app.credit_card_management.entity.CreditCard;
import com.app.credit_card_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findByUser(User user);
}
