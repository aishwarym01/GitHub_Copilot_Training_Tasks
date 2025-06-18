package com.example.transactionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transactionservice.entity.TransactionHistory;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    List<TransactionHistory> findByCustomerId(Long customerId);
}
