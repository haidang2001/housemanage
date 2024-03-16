package com.training0802.demo.controller;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.service.mysql.MysqlHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/house")
@CrossOrigin
public class HouseController {
    @Autowired
    private MysqlHouseServiceImpl houseServiceImp;

    @GetMapping
    @PreAuthorize("hasAuthority('admin')")
    public List<HouseResponse> getHouses() {
        return houseServiceImp.getHouses();
    }
    @GetMapping("/page")
    public Page<House> getHousesPage(@RequestParam Integer page, @RequestParam Integer size) {
        return houseServiceImp.getHousesPage(page,size);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> getHouseDetail(@PathVariable Long id) {
        try {
            HouseResponse houseResponseDetail = houseServiceImp.getHouseDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Get detail of house with id: " + id, houseResponseDetail)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
//    @GetMapping("/{filename}")
//    public ResponseEntity<MessageResponse> loadImage(@PathVariable String filename) {
//        try {
//            byte[] bytes = houseServiceImp.loadImage(filename);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new MessageResponse(0, "Get detail of house with id: ",bytes )
//            );
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new MessageResponse(1, e.getMessage(), "")
//            );
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @GetMapping(value = "/image/{idHouse}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage (@PathVariable Long idHouse) throws IOException {
        return houseServiceImp.loadImage(idHouse);
    }
    @PostMapping()
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> addHouse(@RequestPart("houseResponse") HouseResponse houseResponse,@RequestPart("image") MultipartFile image) {
        try {
            HouseResponse houseResponse1 = houseServiceImp.addHouse(houseResponse,image);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Add new house successfully", houseResponse1)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> deleteHouse(@PathVariable Long id) {
        try {
            houseServiceImp.deleteHouse(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Delete house successfully with id: " + id, "")
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<MessageResponse> updateHouse(@RequestBody HouseResponse houseResponse, @PathVariable Long id) {
        try {
            HouseResponse houseResponse1 = houseServiceImp.updateHouse(houseResponse, id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Update house sucessfully", houseResponse1)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
    @GetMapping("/income")
    public ResponseEntity<MessageResponse> getHouseIncome(@PathVariable Long id) {
        try {
            HouseResponse houseResponseDetail = houseServiceImp.getHouseDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new MessageResponse(0, "Get detail of house with id: " + id, houseResponseDetail)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new MessageResponse(1, e.getMessage(), "")
            );
        }
    }
}
