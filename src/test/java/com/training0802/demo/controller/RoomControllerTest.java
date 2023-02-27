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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

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
    public void test_getListRoom(){
        House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
        List<RoomResponse> listRoom = Arrays.asList(
                new RoomResponse(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house"),
                new RoomResponse(2L,"Room102",house,3,20,"picture2","occupied","cleaning",10000,"room of house")
        );
        List<RoomResponse> listRoomMock = listRoom;
        Mockito.when(roomService.getRooms()).thenReturn(listRoomMock);
        List<RoomResponse> actualRoomList = roomController.getRooms();
        Assertions.assertThat(actualRoomList).isEqualTo(listRoomMock);
    }
    @Test
    public void test_getDetailRoom() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/room/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

}