package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.service.mysql.AccountServiceImpl;
import com.training0802.demo.service.mysql.MysqlHouseServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(HouseController.class)
@AutoConfigureMockMvc(addFilters=false)
public class HouseControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private HouseController houseController;
    @Mock
    private MysqlHouseServiceImpl mysqlHouseService;
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(houseController).build();
    }
    @Test
    public void test_getListHouse(){
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        List<Room> listRoom = Arrays.asList(
                new Room(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house"),
                new Room(2L,"Room102",house,3,20,"picture2","occupied","cleaning",10000,"room of house")
        );
        List<HouseResponse> listHouse = Arrays.asList(
                new HouseResponse(1L,"Truong chinh","Chinh","02/08/2022","Hai","available","house at TC",listRoom),
                new HouseResponse(2L,"Truong chinh","Chinh","02/08/2022","Hai","available","house at TC",listRoom)
        );
        List<HouseResponse> listHouseMock = listHouse;
        Mockito.when(mysqlHouseService.getHouses()).thenReturn(listHouseMock);
        List<HouseResponse> actualHouseList = houseController.getHouses();
        Assertions.assertThat(actualHouseList).isEqualTo(listHouseMock);
    }
    @Test
    public void test_getDetailHouse() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/house/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

}