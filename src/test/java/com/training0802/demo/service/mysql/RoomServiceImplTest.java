//package com.training0802.demo.service.mysql;
//
//import com.training0802.demo.dto.RoomResponse;
//import com.training0802.demo.model.mysql.House;
//import com.training0802.demo.model.mysql.Room;
//import com.training0802.demo.model.mysql.RoomSer;
//import com.training0802.demo.repository.RoomRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.modelmapper.ModelMapper;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//@RunWith(MockitoJUnitRunner.class) //junit4
//public class RoomServiceImplTest {
//    @InjectMocks //test cái nào dùng injectmocks
//    private RoomServiceImpl roomService;
//    @Mock
//    private RoomRepository roomRepository;
//    @Mock
//    private ModelMapper modelMapper;
//    @Before
//    public void initMocks() {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    public void test_getRooms_givenRooms_thenReturnSize(){
//        House house = new House();
//        List<RoomSer> listRoomSer = Arrays.asList(
//                new RoomSer(),
//                new RoomSer()
//        );
//        List<Room> listRoom = Arrays.asList(
//                new Room(1L,"Room101",house,4,20,"picture","occupied",listRoomSer,10000,"room of house"),
//                new Room(2L,"Room102",house,3,20,"picture2","occupied",listRoomSer,10000,"room of house")
//        );
//        when(roomRepository.findAll()).thenReturn(listRoom);
//        assertEquals(2,roomService.getRooms().size());
//        verify(roomRepository).findAll();
//    }
//
//    @Test
//    public void test_getDetailRoom_givenRoomId_thenReturnRoomName(){
//        Long id = 1L;
//        List<RoomSer> listRoomSer = Arrays.asList(
//                new RoomSer(),
//                new RoomSer()
//        );
//        //create mock
//        Room roomByMock = new Room(1L,"Room101",4,20,"picture","occupied",listRoomSer,10000,"room of house");
//        //define behavior repo
//        doReturn(Optional.of(roomByMock)).when(roomRepository).findById(id);
//        //call service
//        RoomResponse roomByService = roomService.getDetailRoom(id);
//        //result
//        assertEquals(roomByMock.getName(),roomByService.getName());
//        //ensure repo is call (optional)
//        verify(roomRepository).findById(id);
//    }
//
//    @Test
//    public void test_addHouse_givenRoom_thenReturnIdGreaterThan0(){
//        //create mock
//        List<RoomSer> listRoomSer = Arrays.asList(
//                new RoomSer(),
//                new RoomSer()
//        );
//        House house = new House();
//        RoomSer roomSer = new RoomSer();
//        Room roomByMock = new Room(1L,"Room101",house,4,20,"picture","occupied",listRoomSer,10000,"room of house");
//        //define behavior repo
//        when(roomRepository.save(roomByMock)).thenReturn(roomByMock);
//        //call service
//        RoomResponse roomRes = new RoomResponse();
//        roomService.addRoom(roomRes);
//        //result
//        Assertions.assertThat(roomRes.getId()).isGreaterThan(0);
//        //ensure repo is call (optional)
//    }
//    @Test
//    public void test_deleteHouse_givenRoomId_thenReturnDeleteOnce(){
//        //create mock
//        House houseMock = new House();
//        //call service
//        roomService.deleteRoom(houseMock.getId());
//        //ensure repo is call (optional)
//        verify(roomRepository,times(1)).deleteById(houseMock.getId());
//    }
//}