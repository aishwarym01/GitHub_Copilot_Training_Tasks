package com.app.credit_card_management.serviceimplementation;

import com.app.credit_card_management.entity.CreditCard;
import com.app.credit_card_management.entity.User;
import com.app.credit_card_management.repository.CreditCardRepository;
import com.app.credit_card_management.serviceinterfaces.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public CreditCard addCard(CreditCard card, User user) {
        card.setUser(user);
        return creditCardRepository.save(card);
    }

    @Override
    public List<CreditCard> getCardsByUser(User user) {
        return creditCardRepository.findByUser(user);
    }

    @Override
    public CreditCard getCardById(Long id) {
        return creditCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));
    }

    @Override
    public CreditCard updateCard(Long id, CreditCard updatedCard) {
        CreditCard existing = getCardById(id);
        existing.setCardHolderName(updatedCard.getCardHolderName());
        existing.setCreditLimit(updatedCard.getCreditLimit());
        existing.setExpiryDate(updatedCard.getExpiryDate());
        return creditCardRepository.save(existing);
    }

    @Override
    public void deleteCard(Long id) {
        creditCardRepository.deleteById(id);
    }
}
