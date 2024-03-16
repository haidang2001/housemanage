//package com.training0802.demo.service.mysql;
//
//import com.training0802.demo.dto.AccountResponse;
//import com.training0802.demo.dto.HouseResponse;
//import com.training0802.demo.model.mysql.Account;
//import com.training0802.demo.model.mysql.House;
//import com.training0802.demo.model.mysql.Room;
//import com.training0802.demo.repository.MysqlHouseRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.modelmapper.ModelMapper;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class) //junit4
//public class MysqlHouseServiceImplTest {
//    @InjectMocks //test cái nào dùng injectmocks
//    private MysqlHouseServiceImpl mysqlHouseService;
//    @Mock
//    private MysqlHouseRepository mysqlHouseRepository;
//    @Mock
//    private ModelMapper modelMapper;
//    @Before
//    public void initMocks() {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    public void test_getHouses_givenListHouse_thenReturnSizeList(){
//        House house = new House();
//        List<Room> listRoom = Arrays.asList(
//                new Room(),
//                new Room()
//        );
//        List<House> listHouse = Arrays.asList(
//                new House(),
//                new House()
//        );
//
//        when(mysqlHouseRepository.findAll())
//                .thenReturn(listHouse);
//        //call service, result
//        assertEquals(2,mysqlHouseService.getHouses().size());
//        //ensure repo call
//        verify(mysqlHouseRepository).findAll();
//    }
//    @Test
//    public void test_getHouseDetail_givenHouse_thenReturnHouseName(){
//        Long id = 1L;
//        //create mock
//        House houseByMock = new House();
//        //define behavior repo
//        doReturn(Optional.of(houseByMock)).when(mysqlHouseRepository).findById(id);
//        //call service
//        HouseResponse houseByService = mysqlHouseService.getHouseDetail(id);
//        //result
//        assertEquals(id,houseByService.getId());
//        assertEquals(houseByMock.getName(),houseByService.getName());
//        //ensure repo is call (optional)
//        verify(mysqlHouseRepository).findById(id);
//    }
////    @Test
////    public void test_addHouse_givenHouse_thenReturnHouseId(){
////        //create mock
////        House houseByMock = new House();
////        //define behavior repo
////        when(mysqlHouseRepository.save(houseByMock)).thenReturn(houseByMock);
////        //call service
////        HouseResponse houseRes = new HouseResponse();
////        mysqlHouseService.addHouse(houseRes);
////        //result
////        Assertions.assertThat(houseRes.getId()).isGreaterThan(0);
////        //ensure repo is call (optional)
////    }
//    @Test
//    public void test_deleteHouse_givenHouseId_thenReturnDeleteOnce(){
//        //create mock
//        House houseMock = new House();
//        //call service
//        mysqlHouseService.deleteHouse(houseMock.getId());
//        //ensure repo is call (optional)
//        verify(mysqlHouseRepository,times(1)).deleteById(houseMock.getId());
//    }
//    @Test
//    public void test_updateHouse_success(){
//        House house = mock(House.class);
//        Mockito.when(mysqlHouseRepository.findById(1L)).thenReturn(Optional.of(house));
//        HouseResponse houseResponse = new HouseResponse();
//        HouseResponse updateHouse = mysqlHouseService.updateHouse(houseResponse, 1L);
//        Assertions.assertThat(updateHouse).isEqualTo(houseResponse);
//        verify(mysqlHouseRepository,times(1)).save(any());
//    }
//    @Test
//    public void test_updateHouse_notFoundId(){
//        House house = mock(House.class);
//        Mockito.when(mysqlHouseRepository.findById(1L)).thenThrow(new RuntimeException());
//        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
////            HouseResponse houseResponse = new HouseResponse();
////            mysqlHouseService.updateHouse(houseResponse, 1L);
//            throw new RuntimeException("not found");
//        });
////        Assertions.assertThat(updateHouse).isEqualTo(houseResponse);
//        assertEquals("not found", runtimeException.getMessage());
//        verify(mysqlHouseRepository,times(0)).save(any());
//    }
//}