package com.example.transactionservice.serviceinterfaces;

import java.util.List;

import com.example.transactionservice.entity.TransactionHistory;

public interface ITransactionService {
    
    void credit(Long customerId, Double amount, String modeOfTransaction);
    void debit(Long customerId, Double amount, String modeOfTransaction);
    Double getBalance(Long customerId);
    List<TransactionHistory> getTransactionHistory(Long customerId);
	double calculateCompoundInterest(double principal, double rate, int time);
	void applyInterest(Long customerId, double rate, int time);

}
