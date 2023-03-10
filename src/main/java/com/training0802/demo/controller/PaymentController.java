package com.training0802.demo.controller;

import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.PaymentMethodGatewayResponse;
import com.training0802.demo.dto.PaymentMethodManualResponse;
import com.training0802.demo.dto.TenantResponse;
import com.training0802.demo.model.mysql.PaymentMethodManual;
import com.training0802.demo.service.mysql.PaymentServiceImpl;
import com.training0802.demo.service.mysql.TenantServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;

    @GetMapping("/manual")
    public List<PaymentMethodManualResponse> getListPaymentMethodManual() {
        return paymentService.getListPaymentMethodManual();
    }

    @GetMapping("/manual/{id}")
    public ResponseEntity<MessageResponse> getPaymentMethodManualDetail(@PathVariable Long id) {
        try {
            PaymentMethodManualResponse paymentMethodManualResponse = paymentService.getPaymentMethodManualDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Get detail of payment manual with id: " + id, paymentMethodManualResponse)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PostMapping("/manual")
    public ResponseEntity<MessageResponse> addPaymentMethodManual(@RequestBody PaymentMethodManualResponse paymentMethodManualResponse) {
        try {
            PaymentMethodManualResponse paymentMethodManualResponse1 = paymentService.addPaymentMethodManual(paymentMethodManualResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add new payment manual successfully", paymentMethodManualResponse1)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @DeleteMapping("/manual/{id}")
    public ResponseEntity<MessageResponse> deletePaymentMethodManual(@PathVariable Long id) {
        try {
            paymentService.deletePaymentMethodManual(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete payment manual successfully with id: " + id, "")
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PutMapping("/manual/{id}")
    public ResponseEntity<MessageResponse> updatePaymentMethodManual(@RequestBody PaymentMethodManualResponse paymentMethodManualResponse, @PathVariable Long id) {
        try {
            PaymentMethodManualResponse paymentMethodManualResponse1 = paymentService.updatePaymentMethodManual(paymentMethodManualResponse, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Update payment manual sucessfully", paymentMethodManualResponse1)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
    @GetMapping("/gateway")
    public List<PaymentMethodGatewayResponse> getListPaymentMethodGateway() {
        return paymentService.getListPaymentMethodGateway();
    }

    @GetMapping("/gateway/{id}")
    public ResponseEntity<MessageResponse> getPaymentMethodGatewayDetail(@PathVariable Long id) {
        try {
            PaymentMethodGatewayResponse paymentMethodGatewayResponse = paymentService.getPaymentMethodGatewayDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Get detail of payment manual with id: " + id, paymentMethodGatewayResponse)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PostMapping("/gateway")
    public ResponseEntity<MessageResponse> addPaymentMethodGateway(@RequestBody PaymentMethodGatewayResponse paymentMethodGatewayResponse) {
        try {
            PaymentMethodGatewayResponse paymentMethodGatewayResponse1 = paymentService.addPaymentMethodManual(paymentMethodGatewayResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add new payment Gateway successfully", paymentMethodGatewayResponse1)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @DeleteMapping("/gateway/{id}")
    public ResponseEntity<MessageResponse> deletePaymentMethodGateway(@PathVariable Long id) {
        try {
            paymentService.deletePaymentMethodGateway(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete payment Gateway successfully with id: " + id, "")
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PutMapping("/gateway/{id}")
    public ResponseEntity<MessageResponse> updatePaymentMethodGateway(@RequestBody PaymentMethodGatewayResponse paymentMethodGatewayResponse, @PathVariable Long id) {
        try {
            PaymentMethodGatewayResponse paymentMethodGatewayResponse1 = paymentService.updatePaymentMethodGateway(paymentMethodGatewayResponse, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Update payment Gateway sucessfully", paymentMethodGatewayResponse1)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
}
