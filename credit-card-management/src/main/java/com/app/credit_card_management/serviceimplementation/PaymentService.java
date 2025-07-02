package com.app.credit_card_management.serviceimplementation;
import com.app.credit_card_management.dto.PaymentDTO;
import com.app.credit_card_management.entity.CreditCard;
import com.app.credit_card_management.entity.Payment;
import com.app.credit_card_management.entity.User;
import com.app.credit_card_management.repository.CreditCardRepository;
import com.app.credit_card_management.repository.PaymentRepository;
import com.app.credit_card_management.repository.UserRepository;
import com.app.credit_card_management.serviceinterfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private EmailService emailService;

    @Override
    public void makePayment(String username, PaymentDTO paymentDTO) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CreditCard card = creditCardRepository.findById(paymentDTO.getCreditCardId())
                .orElseThrow(() -> new RuntimeException("Credit card not found"));

        if (!card.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized payment attempt");
        }

        BigDecimal paymentAmount = paymentDTO.getAmount();
        if (paymentAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Payment amount must be positive");
        }

        if (paymentAmount.compareTo(card.getBalance()) > 0) {
            throw new RuntimeException("Payment exceeds outstanding balance");
        }

        // Update balance
        card.setBalance(card.getBalance().subtract(paymentAmount));
        creditCardRepository.save(card);

        // Record payment
        Payment payment = new Payment();
        payment.setAmount(paymentAmount);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setCreditCard(card);
        payment.setUser(user);
        paymentRepository.save(payment);

        // Send payment confirmation
        emailService.sendEmail(
        		user.getEmail(),
                "Payment Confirmation",
                "Your payment of ₹" + paymentAmount + " was successful for card ending in " +
                        card.getCardNumber().substring(card.getCardNumber().length() - 4)
        );

        // Check for credit limit warning
        BigDecimal usage = card.getBalance().divide(card.getCreditLimit(), 2, BigDecimal.ROUND_HALF_UP);
        if (usage.compareTo(new BigDecimal("0.9")) >= 0) {
            emailService.sendEmail(
                    user.getUsername(),
                    "Credit Limit Warning",
                    "Warning: Your credit card balance has reached 90% of your credit limit."
            );
        }
    }
}

