package com.training0802.demo.controller;

import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.RentalFeeHouseResponse;
import com.training0802.demo.service.mysql.RentalFeeHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentalFeeHouse")
public class RentalFeeHouseController {

    @Autowired
    private RentalFeeHouseServiceImpl rentalFeeHouseService;

    @GetMapping
    public List<RentalFeeHouseResponse> getListRentalFeeHouse() {
        List<RentalFeeHouseResponse> listRentalFeeHouse = rentalFeeHouseService.getListRentalFeeHouse();
        return listRentalFeeHouse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getRentalFeeHouseDetail(@PathVariable Long id) {
        try {
            RentalFeeHouseResponse rentalFeeHouseResponse = rentalFeeHouseService.getRentalFeeHouseDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Get detail of rental fee house with id: " + id, rentalFeeHouseResponse)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PostMapping
    public ResponseEntity<MessageResponse> addRentalFeeHouse(@RequestBody RentalFeeHouseResponse rentalFeeHouseResponse) {
        try{
            RentalFeeHouseResponse rentalFeeHouseResponse1 = rentalFeeHouseService.addRetalFeeHouse(rentalFeeHouseResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add new rental fee house successfully", rentalFeeHouseResponse1)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRentalFeeHouse(@PathVariable Long id) {
        try {
            rentalFeeHouseService.deleteRentalFeeHouse(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete rental fee house successfully" + id, "")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateRentalHouse(@RequestBody RentalFeeHouseResponse rentalFeeHouseResponse, @PathVariable Long id) {
        try {
            rentalFeeHouseService.updateRentalFeeHouse(rentalFeeHouseResponse, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Update rental fee house sucessfully", rentalFeeHouseResponse)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

}
