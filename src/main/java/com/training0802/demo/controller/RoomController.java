package com.training0802.demo.controller;

import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.service.mysql.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomServiceImpl roomServiceImpl;

    @GetMapping
    public List<RoomResponse> getRooms(){
        return roomServiceImpl.getRooms();
    }
    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getRoomDetail(@PathVariable Long id){
        try {
            RoomResponse roomResponseFound = roomServiceImpl.getDetailRoom(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Get detail of room with id: "+ id,roomResponseFound)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }

    }
    @PostMapping
    public ResponseEntity<MessageResponse> addRoom(@RequestBody RoomResponse roomResponse){
        try{
            RoomResponse roomResponse1 = roomServiceImpl.addRoom(roomResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Add new room sucessfully",roomResponse1)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRoom(@PathVariable Long id){
        try {
            roomServiceImpl.deleteRoom(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Delete room successfully","")
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateRoom(@RequestBody RoomResponse roomResponse, @PathVariable Long id){
        try {
            RoomResponse roomResponse1 = roomServiceImpl.updateRoom(roomResponse, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0,"Update room sucessfully",roomResponse1)
            );
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1,e.getMessage(),"")
            );
        }
    }
}
