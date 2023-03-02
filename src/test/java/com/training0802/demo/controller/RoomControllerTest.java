package com.training0802.demo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.dto.MessageResponse;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    private Gson gson;
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }
    @Test
    public void test_getListRoom_givenListRoom_thenReturnListRoom() throws Exception {
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        List<RoomResponse> listRoom = Arrays.asList(
                new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house"),
                new RoomResponse(2L,"Room102",house,3,20,"picture2","occupied","cleaning",10000,"room of house")
        );
        List<RoomResponse> listRoomMock = listRoom;
        Mockito.when(roomService.getRooms()).thenReturn(listRoomMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/room"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("[{\"id\":1,\"name\":\"Room101\",\"house\":{\"id\":1,\"location\":\"Truong Chinh\",\"name\":\"TC house\",\"establishDate\":\"08/02/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"avalable\",\"description\":\"this is house at TC\",\"image\":null},\"floor\":4,\"area\":20,\"image\":\"picture\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"},{\"id\":2,\"name\":\"Room102\",\"house\":{\"id\":1,\"location\":\"Truong Chinh\",\"name\":\"TC house\",\"establishDate\":\"08/02/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"avalable\",\"description\":\"this is house at TC\",\"image\":null},\"floor\":3,\"area\":20,\"image\":\"picture2\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"}]"));

    }
    @Test
    public void test_getDetailRoom_givenIdRoom_thenReturnRoomDetail() throws Exception {
        Long id = 1L;
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        RoomResponse roomResponse = new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");

        Mockito.when(roomService.getDetailRoom(id)).thenReturn(roomResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/room/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(content().string("{\"status\":0,\"message\":\"Get detail of room with id: 1\",\"data\":{\"id\":1,\"name\":\"Room101\",\"house\":{\"id\":1,\"location\":\"Truong Chinh\",\"name\":\"TC house\",\"establishDate\":\"08/02/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"avalable\",\"description\":\"this is house at TC\",\"image\":null},\"floor\":4,\"area\":20,\"image\":\"picture\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"}}"));
    }
    @Test
    public void test_getDetailRoom_givenIdRoom_thenReturnRoomNotFound() throws Exception {
        Long id = 1L;
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        RoomResponse roomResponse = new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");

        Mockito.when(roomService.getDetailRoom(id)).thenThrow(new RuntimeException("Not found room with this id: "+id ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/room/"+id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(content().string("{\"status\":1,\"message\":\"Not found room with this id: 1\",\"data\":\"\"}"));
    }
    @Test
    public void test_addRoom_givenNewRoom_thenReturnRoomAdded() throws Exception {
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        RoomResponse roomResponse = new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");
        String jsonContent = gson.toJson(roomResponse);

        MessageResponse messageExpected = new MessageResponse(0,"Add new room sucessfully",roomResponse);
        String jsonExpected = gson.toJson(messageExpected);

//        Mockito.doNothing().when(roomService).addRoom(roomResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/room").contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept("application/json"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonExpected));

    }
    @Test
    public void test_deleteRoom_givenIdRoom_thenReturnRoomNotFound() throws Exception {
        Long id = 2L;
        MessageResponse messageExpected = new MessageResponse(1,"Not exist room with id: 2","");
        String jsonExpected = gson.toJson(messageExpected);
        Mockito.doThrow(new RuntimeException()).when(roomService).deleteRoom(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/room/"+id))
                .andExpect(status().isNotFound())
                .andExpect(content().json(jsonExpected));
    }
    @Test
    public void test_updateRoom_givenIdRoom_thenReturnRoomResponse() throws Exception {
        Long id = 1L;
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        RoomResponse roomResponse = new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");
        String jsonContent = gson.toJson(roomResponse);
        MessageResponse messageExpected = new MessageResponse(0,"Update room sucessfully",roomResponse);
        String jsonExpect = gson.toJson(messageExpected);
//        Mockito.when(roomService.updateRoom(roomResponse,id)).thenReturn(roomResponse);
        mockMvc.perform(put("/api/room/"+id).contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonExpect));
    }
    @Test
    public void test_updateRoom_givenIdRoom_thenReturnRoomNotFound() throws Exception {
        Long id = 1L;
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        RoomResponse roomResponse = new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house");
        String jsonContent = gson.toJson(roomResponse);
        MessageResponse messageExpected = new MessageResponse(1,"Not found room with this id","");
        String jsonExpect = gson.toJson(messageExpected);
        Mockito.when(roomService.updateRoom(any(RoomResponse.class),eq(id))).thenThrow(new RuntimeException("Not found room with this id"));
        mockMvc.perform(put("/api/room/"+id).contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonExpect));
    }
}