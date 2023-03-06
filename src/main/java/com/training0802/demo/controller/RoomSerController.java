package com.training0802.demo.controller;

import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.dto.RoomSerResponse;
import com.training0802.demo.service.mysql.RoomSerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomser")
public class RoomSerController {
    @Autowired
    private RoomSerServiceImpl roomSerService;

    @GetMapping
    public List<RoomSerResponse> getRoomSers() {
        return roomSerService.getListRoomService();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getRoomSerDetail(@PathVariable Long id) {
        try {
            RoomSerResponse roomSerResponseFound = roomSerService.getDetailRoomService(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Get detail of room ser with id: " + id, roomSerResponseFound)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }

    }

    @PostMapping
    public ResponseEntity<MessageResponse> addRoomSer(@RequestBody RoomSerResponse roomSerResponse) {
        try{
            RoomSerResponse roomSerResponse1 = roomSerService.addRoomService(roomSerResponse);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add new room ser sucessfully", roomSerResponse1)
            );
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(1,e.getMessage(), "")
            );
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRoomSer(@PathVariable Long id) {
        try {
            roomSerService.deleteRoomService(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete room ser successfully", "")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateRoomSer(@RequestBody RoomSerResponse roomSerResponse, @PathVariable Long id) {
        try {
            roomSerService.updateRoomService(roomSerResponse, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Update room sucessfully", roomSerResponse)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
}
