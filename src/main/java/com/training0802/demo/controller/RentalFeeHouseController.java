package com.training0802.demo.controller;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.RentalFeeHouseResponse;
import com.training0802.demo.service.HouseService;
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
//    @PreAuthorize("hasAuthority('admin')")
    public List<RentalFeeHouseResponse> getListRentalFeeHouse(){
        return rentalFeeHouseService.getListRentalFeeHouse();
    }
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> getRentalFeeHouseDetail(@PathVariable Long id){
        try {
            RentalFeeHouseResponse rentalFeeHouseResponse = rentalFeeHouseService.getRentalFeeHouseDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Get detail of rental fee house with id: "+ id, rentalFeeHouseResponse)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }
    @PostMapping(consumes = {"application/json"})
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> addRentalFeeHouse(@RequestBody RentalFeeHouseResponse rentalFeeHouseResponse){
        rentalFeeHouseService.addRetalFeeHouse(rentalFeeHouseResponse);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Add new rental fee house successfully",rentalFeeHouseResponse)
        );
    }
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> deleteRentalFeeHouse(@PathVariable Long id){
        try{
            rentalFeeHouseService.deleteRentalFeeHouse(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Delete rental fee house successfully" + id,"")
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
    public ResponseEntity<MessageResponse> updateRentalHouse(@RequestBody RentalFeeHouseResponse rentalFeeHouseResponse,@PathVariable Long id){
        try{
            rentalFeeHouseService.updateRentalFeeHouse(rentalFeeHouseResponse,id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Update rental fee house sucessfully",rentalFeeHouseResponse)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }

}
