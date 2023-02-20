package com.training0802.demo.controller;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.service.HouseService;
import com.training0802.demo.service.MysqlHouseServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAuthority;

@RestController
@RequestMapping("/api/house")

public class HouseController {
    @Autowired
    private HouseService houseServiceImp;
    @Autowired
    private MysqlHouseServiceImp mysqlHouseServiceImp;
    @GetMapping
    public List<HouseResponse> getHouses(){
        return houseServiceImp.getHouses();
    }
    @PostMapping
    public ResponseEntity<MessageResponse> addHouse(@RequestBody HouseResponse houseResponse){
        houseResponse.getId();
        mysqlHouseServiceImp.addHouse(houseResponse);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Add new house sucessfully",houseResponse)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteHouse(@PathVariable Long id){
        mysqlHouseServiceImp.deleteHouse(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Delete house successfully" + id,"")
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateHouse(@RequestBody HouseResponse houseResponse,@PathVariable Long id){
        mysqlHouseServiceImp.updateHouse(houseResponse,id);
        return ResponseEntity.status(HttpStatus.OK).body(
            new MessageResponse(0,"Update house sucessfully",houseResponse)
        );
    }

}
