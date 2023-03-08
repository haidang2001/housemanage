package com.training0802.demo.controller;

import com.training0802.demo.dto.AccResponse;
import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.model.mysql.Acc;
import com.training0802.demo.service.mysql.AccServiceImpl;
import com.training0802.demo.service.mysql.AccountServiceImpl;
import jakarta.persistence.EntityNotFoundException;
//import jakarta.validation.Valid;
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

//    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
//    public ResponseEntity<MessageResponse> getAccount(@PathVariable Long id) {
//        try {
//            AccountResponse accountFound = accountService.getOneAccount(id);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new MessageResponse(0, "Account found with id: " + id, accountFound)
//            );
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new MessageResponse(1, e.getMessage(), "")
//            );
//        }
//    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addAccount(@RequestBody @Valid AccResponse accResponse, BindingResult bindingResult) {
//        Acc existingUser = userService.findUserByEmail(userDto.getEmail());
//
//        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
//            result.rejectValue("email", null,
//                    "There is already an account registered with the same email");
//        }
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, bindingResult.getAllErrors().get(0).getDefaultMessage(), "")
            );
        };
        try {
            AccResponse accResponse1 = accService.addAcc(accResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add successful account", accResponse1)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }


    }

//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
//    public ResponseEntity<MessageResponse> deleteAccount(@PathVariable Long id) {
//        try {
//            accountService.deleteAccount(id);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new MessageResponse(0, "Delete account successfully with id:" + id, id)
//            );
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new MessageResponse(1, e.getMessage(), "")
//            );
//        }
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
//    public ResponseEntity<MessageResponse> updateAccount(@RequestBody AccountResponse accountResponse, @PathVariable Long id) {
//        try {
//            AccountResponse accountResponse1 = accountService.updateAccount(accountResponse, id);
//            accountResponse.setId(id);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new MessageResponse(0, "Update account successfully with id: " + id, accountResponse1)
//            );
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new MessageResponse(1, e.getMessage(), "")
//            );
//        }
//    }
}
