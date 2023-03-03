package com.training0802.demo.service;

import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.dto.TenantResponse;

import java.util.List;

public interface TenantService {
    List<TenantResponse> getListTenant();
    TenantResponse getTenantDetail(Long id);
    TenantResponse addTenant(TenantResponse tenantResponse);
    void deleteTenant(Long id);
    TenantResponse updateTenant(TenantResponse tenantResponse, Long id);

}
