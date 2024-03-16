//package com.training0802.demo.controller;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.training0802.demo.dto.AccountResponse;
//import com.training0802.demo.dto.HouseResponse;
//import com.training0802.demo.dto.MessageResponse;
//import com.training0802.demo.dto.RoomResponse;
//import com.training0802.demo.model.mysql.House;
//import com.training0802.demo.model.mysql.Room;
//import com.training0802.demo.service.mysql.AccountServiceImpl;
//import com.training0802.demo.service.mysql.MysqlHouseServiceImpl;
//import org.assertj.core.api.Assertions;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(HouseController.class)
//@AutoConfigureMockMvc(addFilters=false)
//public class HouseControllerTest {
//    private MockMvc mockMvc;
//    @InjectMocks
//    private HouseController houseController;
//    @Mock
//    private MysqlHouseServiceImpl mysqlHouseService;
//    private Gson gson;
//    @Before
//    public void setUp() throws Exception{
//        mockMvc = MockMvcBuilders.standaloneSetup(houseController).build();
//
//        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
//        gson = builder.create();
//    }
//    @Test
//    public void test_getListHouse_givenListHouse_thenReturnListHouse() throws Exception {
//        House house = new House();
//        List<Room> listRoom = Arrays.asList(
//                new Room(),
//                new Room()
//        );
//        List<HouseResponse> listHouse = Arrays.asList(
//                new HouseResponse(),
//                new HouseResponse()
//        );
//        List<HouseResponse> listHouseMock = listHouse;
//        Mockito.when(mysqlHouseService.getHouses()).thenReturn(listHouseMock);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/house"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(content().string("[{\"id\":1,\"location\":\"Truong chinh\",\"name\":\"Chinh\",\"establishDate\":\"02/08/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"available\",\"description\":\"house at TC\",\"image\":null,\"roomList\":[{\"id\":1,\"name\":\"Room101\",\"floor\":4,\"area\":20,\"image\":\"picture\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"},{\"id\":2,\"name\":\"Room102\",\"floor\":3,\"area\":20,\"image\":\"picture2\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"}]},{\"id\":2,\"location\":\"Truong chinh\",\"name\":\"Chinh\",\"establishDate\":\"02/08/2022\",\"totalRooms\":0,\"manager\":\"Hai\",\"status\":\"available\",\"description\":\"house at TC\",\"image\":null,\"roomList\":[{\"id\":1,\"name\":\"Room101\",\"floor\":4,\"area\":20,\"image\":\"picture\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"},{\"id\":2,\"name\":\"Room102\",\"floor\":3,\"area\":20,\"image\":\"picture2\",\"status\":\"occupied\",\"service\":\"cleaning\",\"rents\":10000,\"description\":\"room of house\"}]}]"));
//
//    }
//    @Test
//    public void test_getDetailHouse_givenIdHouse_thenReturnHouseFound() throws Exception {
//        Long id = 1L;
//        HouseResponse houseResponse = new HouseResponse();
//        MessageResponse expectedMessage = new MessageResponse(0,"Get detail of house with id: "+id,houseResponse);
//        String jsonExpected = gson.toJson(expectedMessage);
//        Mockito.when(mysqlHouseService.getHouseDetail(id)).thenReturn(houseResponse);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/house/"+id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(content().json(jsonExpected));
//    }
//    @Test
//    public void test_getDetailHouse_givenIdHouse_thenReturnHouseNotFound() throws  Exception{
//        Long id = 1L;
//
//        MessageResponse expectedMessage = new MessageResponse(1,"Not found this house with id: " + id,"");
//        String jsonExpected = gson.toJson(expectedMessage);
//        Mockito.doThrow(new RuntimeException("Not found this house with id: " + id)).when(mysqlHouseService).getHouseDetail(id);
//        mockMvc.perform(get("/api/house/"+id))
//                .andExpect(status().isNotFound())
//                .andExpect(content().json(jsonExpected));
//    }
//
//    @Test
//    public void test_addHouse_givenNewHouse_thenReturnHouseAdded() throws Exception {
//            HouseResponse houseResponse = new HouseResponse();
//        String jsonContent = gson.toJson(houseResponse);
//
//        MessageResponse expectedMessage = new MessageResponse(0,"Add new house successfully",houseResponse);
//        String jsonExpected = gson.toJson(expectedMessage);
//
////        Mockito.when(mysqlHouseService.addHouse(houseResponse)).thenReturn(houseResponse);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/house").contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept("application/json"))
//                .andDo(print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(jsonExpected))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//    @Test
//    public void test_deleteHouse_givenIdHouse_thenReturnHouseNotFound() throws Exception {
//        Long id = 1L;
//        MessageResponse messageExpected = new MessageResponse(1,"Not exist house with id: 1","");
//        String jsonExpected = gson.toJson(messageExpected);
//        Mockito.doThrow(new RuntimeException()).when(mysqlHouseService).deleteHouse(1L);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/house/"+id))
//                .andExpect(status().isNotFound())
//                .andExpect(content().json(jsonExpected));
//    }
//    @Test
//    public void test_updateHouse_givenIdHouse_thenReturnHouseNotFound() throws Exception {
//        Long id = 1L;
//        HouseResponse houseResponse = new HouseResponse();
//        String jsonContent = gson.toJson(houseResponse);
//
//        MessageResponse messageExpected = new MessageResponse(1,"Not found house with id: " +id,"");
//        String jsonExpected = gson.toJson(messageExpected);
//
//        Mockito.when(mysqlHouseService.updateHouse(any(HouseResponse.class),eq(id))).thenThrow(new RuntimeException("Not found house with id: " +id));
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/house/"+id).contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(jsonExpected));
//    }
//    @Test
//    public void test_updateHouse_givenIdHouse_thenReturnHouseUpdated() throws Exception {
//        Long id = 1L;
//        HouseResponse houseResponse = new HouseResponse();
//        String jsonContent = gson.toJson(houseResponse);
//
//        MessageResponse messageExpected = new MessageResponse(0,"Update house sucessfully",houseResponse);
//        String jsonExpected = gson.toJson(messageExpected);
//
////        Mockito.when(mysqlHouseService.updateHouse(any(HouseResponse.class),eq(id))).thenReturn(houseResponse);
//        mockMvc.perform(MockMvcRequestBuilders.put("/api/house/"+id).contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(jsonExpected));
//    }
//}