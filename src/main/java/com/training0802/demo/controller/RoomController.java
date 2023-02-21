package com.training0802.demo.controller;

import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.service.RoomServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomServiceImp roomServiceImp;

    @GetMapping
    public List<RoomResponse> getRooms(){
        return roomServiceImp.getRooms();
    }
    @PostMapping
    public ResponseEntity<MessageResponse> addRoom(@RequestBody RoomResponse roomResponse){
        roomServiceImp.addRoom(roomResponse);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Add new room sucessfully",roomResponse)
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRoom(@PathVariable Long id){
        roomServiceImp.deleteRoom(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Delete room successfully","")
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateRoom(@RequestBody RoomResponse roomResponse, @PathVariable Long id){
        roomServiceImp.updateRoom(roomResponse,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new MessageResponse(0,"Update room sucessfully",roomResponse)
        );
    }
}
