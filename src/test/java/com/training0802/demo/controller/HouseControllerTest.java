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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void test_getListHouse() throws Exception {
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
//        List<HouseResponse> actualHouseList = houseController.getHouses();
//        Assertions.assertThat(actualHouseList).isEqualTo(listHouseMock);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/house"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("[{\"id\":1,\"location\":\"Truong chinh\",\"name\":\"Chinh\",\"establishDate\":\"02/08/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"available\",\"description\":\"house at TC\",\"image\":null,\"roomList\":[{\"id\":1,\"name\":\"Room101\",\"floor\":4,\"area\":20,\"image\":\"picture\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"},{\"id\":2,\"name\":\"Room102\",\"floor\":3,\"area\":20,\"image\":\"picture2\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"}]},{\"id\":2,\"location\":\"Truong chinh\",\"name\":\"Chinh\",\"establishDate\":\"02/08/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"available\",\"description\":\"house at TC\",\"image\":null,\"roomList\":[{\"id\":1,\"name\":\"Room101\",\"floor\":4,\"area\":20,\"image\":\"picture\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"},{\"id\":2,\"name\":\"Room102\",\"floor\":3,\"area\":20,\"image\":\"picture2\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"}]}]"));

    }
    @Test
    public void test_getDetailHouse() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/house/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(content().string("{\"status\":0,\"message\":\"Get detail of house with id: 1\",\"data\":null}"));
    }

    @Test
    public void test_addHouse() throws Exception {
        HouseResponse houseResponse = new HouseResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang");
        mysqlHouseService.addHouse(houseResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/house").accept("application/json"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(""))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void test_deleteHouse() throws Exception {
        Long id = 1L;
        Mockito.doThrow(new RuntimeException("not found")).when(mysqlHouseService).deleteHouse(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/house/"+id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void test_updateHouse() throws Exception {
        Long id = 1L;
        HouseResponse houseResponse = new HouseResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang");
//        Mockito.when(mysqlHouseService.updateHouse(houseResponse,id)).thenReturn(houseResponse);
        mockMvc.perform(put("/api/account/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(""));
    }

}