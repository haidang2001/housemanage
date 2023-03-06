package com.training0802.demo.controller;

import com.training0802.demo.dto.InvoiceResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.TenantResponse;
import com.training0802.demo.service.InvoiceService;
import com.training0802.demo.service.mysql.TenantServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<InvoiceResponse> getListInvoice() {
        return invoiceService.getListInvoice();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getDetailInvoice(@PathVariable Long id) {
        try {
            InvoiceResponse invoiceResponse = invoiceService.getDetailInvoice(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Get detail of invoice with id: " + id, invoiceResponse)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PostMapping
    public ResponseEntity<MessageResponse> addTenant(@RequestBody InvoiceResponse invoiceResponse) {
        try{
            InvoiceResponse invoiceResponse1 = invoiceService.addInvoice(invoiceResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add new invoice successfully", invoiceResponse1)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete invoice successfully" + id, "")
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateInvoice(@RequestBody InvoiceResponse invoiceResponse, @PathVariable Long id) {
        try {
            invoiceService.updateInvoice(invoiceResponse, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Update invoice sucessfully", invoiceResponse)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

}
