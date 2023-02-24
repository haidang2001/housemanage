package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.repository.MysqlHouseRepository;
import com.training0802.demo.repository.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class) //junit4
public class RoomServiceImplTest {
    @InjectMocks //test cái nào dùng injectmocks
    private RoomServiceImpl roomService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private ModelMapper modelMapper;
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test_getRooms_givenRooms_thenReturnSize(){
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        List<Room> listRoom = Arrays.asList(
                new Room(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house"),
                new Room(2L,"Room102",house,3,20,"picture2","occupied","cleaning",10000,"room of house")
        );
        when(roomRepository.findAll()).thenReturn(listRoom);
        assertEquals(2,roomService.getRooms().size());
        verify(roomRepository).findAll();
    }

    @Test
    public void test_getDetailRoom_givenRoomId_thenReturnRoomName(){
        Long id = 1L;

        //create mock
        Room roomByMock = new Room(1L,"Room101",4,20,"picture","occupied","cleaning",10000,"room of house");
        //define behavior repo
        doReturn(Optional.of(roomByMock)).when(roomRepository).findById(id);
        //call service
        RoomResponse roomByService = roomService.getDetailRoom(id);
        //result
        assertEquals(roomByMock.getName(),roomByService.getName());
        //ensure repo is call (optional)
        verify(roomRepository).findById(id);
    }

    @Test
    public void addHouseTest(){
        //create mock

        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        Room roomByMock = new Room(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");
        //define behavior repo
        when(roomRepository.save(roomByMock)).thenReturn(roomByMock);
        //call service
        RoomResponse roomRes = new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");
        roomService.addRoom(roomRes);
        //result
        Assertions.assertThat(roomRes.getId()).isGreaterThan(0);
        //ensure repo is call (optional)
    }
    @Test
    public void deleteHouseTest(){
        //create mock
        House houseMock = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        //call service
        roomService.deleteRoom(houseMock.getId());
        //ensure repo is call (optional)
        verify(roomRepository,times(1)).deleteById(houseMock.getId());
    }
}