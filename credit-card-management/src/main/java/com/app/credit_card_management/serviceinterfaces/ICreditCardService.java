package com.app.credit_card_management.serviceinterfaces;

import com.app.credit_card_management.entity.CreditCard;
import com.app.credit_card_management.entity.User;

import java.util.List;

public interface ICreditCardService {
    CreditCard addCard(CreditCard card, User user);
    List<CreditCard> getCardsByUser(User user);
    CreditCard getCardById(Long id);
    CreditCard updateCard(Long id, CreditCard updatedCard);
    void deleteCard(Long id);
}
