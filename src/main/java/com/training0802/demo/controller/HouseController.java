package com.training0802.demo.controller;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/house")

public class HouseController {
    @Autowired
    private HouseService houseServiceImp;

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public List<HouseResponse> getHouses(){
        return houseServiceImp.getHouses();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> getHouseDetail(@PathVariable Long id){
        try {
            HouseResponse houseResponseDetail = houseServiceImp.getHouseDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Get detail of house with id: "+ id, houseResponseDetail)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }
    @PostMapping
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> addHouse(@RequestBody HouseResponse houseResponse){
        houseServiceImp.addHouse(houseResponse);
        return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Add new house successfully",houseResponse)
        );
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> deleteHouse(@PathVariable Long id){
        try{
            houseServiceImp.deleteHouse(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Delete house successfully" + id,"")
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,"Not exist house with id: " +id,"")
            );
        }
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> updateHouse(@RequestBody HouseResponse houseResponse,@PathVariable Long id){
        try{
            houseServiceImp.updateHouse(houseResponse,id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Update house sucessfully",houseResponse)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }

}
