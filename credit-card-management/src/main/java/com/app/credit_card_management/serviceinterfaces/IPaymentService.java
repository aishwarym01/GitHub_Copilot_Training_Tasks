package com.app.credit_card_management.serviceinterfaces;

import com.app.credit_card_management.dto.PaymentDTO;

public interface IPaymentService {
    void makePayment(String username, PaymentDTO paymentDTO);
}
