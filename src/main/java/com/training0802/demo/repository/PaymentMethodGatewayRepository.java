package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.PaymentMethodGateway;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodGatewayRepository extends JpaRepository<PaymentMethodGateway, Long> {
}
