package com.training0802.demo.controller;

import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.TenantResponse;
import com.training0802.demo.service.mysql.TenantServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {
    @Autowired
    private TenantServiceImpl tenantService;

    @GetMapping
    public List<TenantResponse> getListTenant() {
        return tenantService.getListTenant();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getTenantDetail(@PathVariable Long id) {
        try {
            TenantResponse tenantResponse = tenantService.getTenantDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Get detail of tenant with id: " + id, tenantResponse)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PostMapping
    public ResponseEntity<MessageResponse> addTenant(@RequestBody TenantResponse tenantResponse) {
        try {
            TenantResponse tenantResponse1 = tenantService.addTenant(tenantResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add new tenant successfully", tenantResponse1)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteTenant(@PathVariable Long id) {
        try {
            tenantService.deleteTenant(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete tenant successfully with id: " + id, "")
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateTenant(@RequestBody TenantResponse tenantResponse, @PathVariable Long id) {
        try {
            tenantService.updateTenant(tenantResponse, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Update tenant sucessfully", tenantResponse)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
}
