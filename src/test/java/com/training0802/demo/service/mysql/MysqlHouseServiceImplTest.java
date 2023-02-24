package com.training0802.demo.service.mysql;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.repository.MysqlHouseRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) //junit4
public class MysqlHouseServiceImplTest {
    @InjectMocks //test cái nào dùng injectmocks
    private MysqlHouseServiceImpl mysqlHouseService;
    @Mock
    private MysqlHouseRepository mysqlHouseRepository;
    @Mock
    private ModelMapper modelMapper;
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void test_getHouses_givenListHouse_thenReturnSizeList(){
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        List<Room> listRoom = Arrays.asList(
                new Room(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house"),
                new Room(2L,"Room102",house,3,20,"picture2","occupied","cleaning",10000,"room of house")
        );
        List<House> listHouse = Arrays.asList(
                new House(1L,"Truong chinh","Chinh","02/08/2022","Hai","available","house at TC",listRoom),
                new House(2L,"Truong chinh","Chinh","02/08/2022","Hai","available","house at TC",listRoom)
        );

        when(mysqlHouseRepository.findAll())
                .thenReturn(listHouse);
        //call service, result
        assertEquals(2,mysqlHouseService.getHouses().size());
        //ensure repo call
        verify(mysqlHouseRepository).findAll();
    }
    @Test
    public void test_getHouseDetail_givenHouse_thenReturnHouseName(){
        Long id = 1L;
        //create mock
        House houseByMock = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        //define behavior repo
        doReturn(Optional.of(houseByMock)).when(mysqlHouseRepository).findById(id);
        //call service
        HouseResponse houseByService = mysqlHouseService.getHouseDetail(id);
        //result
        assertEquals(id,houseByService.getId());
        assertEquals(houseByMock.getName(),houseByService.getName());
        //ensure repo is call (optional)
        verify(mysqlHouseRepository).findById(id);
    }
    @Test
    public void test_addHouse_givenHouse_thenReturnHouseId(){
        //create mock
        House houseByMock = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        //define behavior repo
        when(mysqlHouseRepository.save(houseByMock)).thenReturn(houseByMock);
        //call service
        HouseResponse houseRes = new HouseResponse(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        mysqlHouseService.addHouse(houseRes);
        //result
        Assertions.assertThat(houseRes.getId()).isGreaterThan(0);
        //ensure repo is call (optional)
    }
    @Test
    public void test_deleteHouse_givenHouseId_thenReturnDeleteOnce(){
        //create mock
        House houseMock = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        //call service
        mysqlHouseService.deleteHouse(houseMock.getId());
        //ensure repo is call (optional)
        verify(mysqlHouseRepository,times(1)).deleteById(houseMock.getId());
    }
}