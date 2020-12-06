package com.ishakantony.springstatemachineexample.repository;

import com.ishakantony.springstatemachineexample.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
