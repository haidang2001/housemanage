package com.training0802.demo.controller;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/house")

public class HouseController {
    @Autowired
    private HouseService houseServiceImp;

    @GetMapping
    public List<HouseResponse> getHouses(){
        return houseServiceImp.getHouses();
    }
    @PostMapping
    public ResponseEntity<MessageResponse> addHouse(@RequestBody HouseResponse houseResponse){
        houseServiceImp.addHouse(houseResponse);
        return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Add new house sucessfully",houseResponse)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteHouse(@PathVariable Long id){
        houseServiceImp.deleteHouse(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Delete house successfully" + id,"")
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateHouse(@RequestBody HouseResponse houseResponse,@PathVariable Long id){
        houseServiceImp.updateHouse(houseResponse,id);
        return ResponseEntity.status(HttpStatus.OK).body(
            new MessageResponse(0,"Update house sucessfully",houseResponse)
        );
    }

}
