package com.app.credit_card_management.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PaymentDTO {
    private Long creditCardId;
    private BigDecimal amount;

}
