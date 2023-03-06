package com.training0802.demo.repository;

import com.training0802.demo.model.mysql.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
