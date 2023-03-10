package com.training0802.demo.controller;

import com.training0802.demo.dto.AccResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.service.mysql.AccServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acc")

public class AccController {
    @Autowired
    private AccServiceImpl accService;

    @GetMapping()
    @PreAuthorize("hasAuthority('admin')")
    public List<AccResponse> getListAccount() {
        return accService.getAcc();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> getAccount(@PathVariable Long id) {
        try {
            AccResponse accResponse = accService.getOneAcc(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Acc found with id: " + id, accResponse)
            );
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addAccount(@RequestBody @Valid AccResponse accResponse, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, bindingResult.getAllErrors().get(0).getDefaultMessage(), "")
            );
        }
        ;
        try {
            AccResponse accResponse1 = accService.addAcc(accResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add successful account", accResponse1)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

}
