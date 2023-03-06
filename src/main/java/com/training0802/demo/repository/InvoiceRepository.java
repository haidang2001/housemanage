package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
