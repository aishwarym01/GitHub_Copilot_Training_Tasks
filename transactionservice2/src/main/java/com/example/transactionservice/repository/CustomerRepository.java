package com.example.transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transactionservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
