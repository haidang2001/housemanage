package com.training0802.demo.controller;

import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.RentalFeeHouseResponse;
import com.training0802.demo.dto.TenantResponse;
import com.training0802.demo.service.mysql.RentalFeeHouseServiceImpl;
import com.training0802.demo.service.mysql.TenantServiceImpl;
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
//    @PreAuthorize("hasAuthority('admin')")
    public List<TenantResponse> getListTenant(){
        return tenantService.getListTenant();
    }
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> getTenantDetail(@PathVariable Long id){
        try {
            TenantResponse tenantResponse = tenantService.getTenantDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Get detail of tenant with id: "+ id, tenantResponse)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> addTenant(@RequestBody TenantResponse tenantResponse){
        tenantService.addTenant(tenantResponse);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Add new tenant successfully",tenantResponse)
        );
    }
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> deleteTenant(@PathVariable Long id){
        try{
            tenantService.deleteTenant(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Delete tenant successfully" + id,"")
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> updateTenant(@RequestBody TenantResponse tenantResponse,@PathVariable Long id){
        try{
            tenantService.updateTenant(tenantResponse,id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Update tenant sucessfully",tenantResponse)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }

}
