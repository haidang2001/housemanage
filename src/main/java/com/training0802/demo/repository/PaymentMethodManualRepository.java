package com.training0802.demo.repository;


import com.training0802.demo.model.mysql.PaymentMethodManual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodManualRepository extends JpaRepository<PaymentMethodManual, Long> {
}
