package com.wheelcredit.Backend.repository;

import com.wheelcredit.Backend.model.SmartPayment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SmartPaymentRepository extends JpaRepository<SmartPayment, Long> {
    List<SmartPayment> findByClientId(Long clientId);
}
