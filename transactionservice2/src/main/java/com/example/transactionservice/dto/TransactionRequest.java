package com.example.transactionservice.dto;


import lombok.Data;

@Data
public class TransactionRequest {
    private Long customerId;
    private Double amount;
    private String modeOfTransaction;
}

