package com.training0802.demo.controller;

import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.RoomResponse;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.service.mysql.MysqlHouseServiceImpl;
import com.training0802.demo.service.mysql.RoomServiceImpl;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(RoomController.class)
@AutoConfigureMockMvc(addFilters=false)
public class RoomControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private RoomController roomController;
    @Mock
    private RoomServiceImpl roomService;
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }
    @Test
    public void test_getListRoom() throws Exception {
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        List<RoomResponse> listRoom = Arrays.asList(
                new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house"),
                new RoomResponse(2L,"Room102",house,3,20,"picture2","occupied","cleaning",10000,"room of house")
        );
        List<RoomResponse> listRoomMock = listRoom;
        Mockito.when(roomService.getRooms()).thenReturn(listRoomMock);
//        List<RoomResponse> actualRoomList = roomController.getRooms();
//        Assertions.assertThat(actualRoomList).isEqualTo(listRoomMock);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/room"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("[{\"id\":1,\"name\":\"Room101\",\"house\":{\"id\":1,\"location\":\"Truong Chinh\",\"name\":\"TC house\",\"establishDate\":\"08/02/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"avalable\",\"description\":\"this is house at TC\",\"image\":null},\"floor\":4,\"area\":20,\"image\":\"picture\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"},{\"id\":2,\"name\":\"Room102\",\"house\":{\"id\":1,\"location\":\"Truong Chinh\",\"name\":\"TC house\",\"establishDate\":\"08/02/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"avalable\",\"description\":\"this is house at TC\",\"image\":null},\"floor\":3,\"area\":20,\"image\":\"picture2\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"}]"));

    }
    @Test
    public void test_getDetailRoom() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/room/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(content().string("{\"status\":0,\"message\":\"Get detail of room with id: 1\",\"data\":null}"));
    }
    @Test
    public void test_addRoom() throws Exception {
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        RoomResponse roomResponse = new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");

        roomService.addRoom(roomResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/room").accept("application/json"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(""))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void test_deleteRoom() throws Exception {
        Long id = 2L;
        Mockito.doThrow(new RuntimeException("not found")).when(roomService).deleteRoom(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/house/"+id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void test_updateAccount() throws Exception {
        Long id = 1L;
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        RoomResponse roomResponse = new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");

//        Mockito.when(mysqlHouseService.updateHouse(houseResponse,id)).thenReturn(houseResponse);
        mockMvc.perform(put("/api/room/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(""));
    }
}