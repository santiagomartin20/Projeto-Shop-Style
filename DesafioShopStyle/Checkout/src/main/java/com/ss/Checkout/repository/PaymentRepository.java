package com.ss.Checkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.Checkout.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
	
}
