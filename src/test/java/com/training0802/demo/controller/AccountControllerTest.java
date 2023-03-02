package com.training0802.demo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.MessageResponse;
import com.training0802.demo.service.mysql.AccountServiceImpl;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc(addFilters=false)
public class AccountControllerTest {
    private MockMvc mockMvc;
    @InjectMocks
    private AccountController accountController;
    @Mock
    private AccountServiceImpl accountService;
    private Gson gson;
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        gson = builder.create();
    }

    @Test
    public void test_getListAccount_givenListAccount_thenReturnListAccount() throws Exception {
        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
        List<AccountResponse> listAccMock = Arrays.asList(accountResponse1);

        //test mockito
        Mockito.when(accountService.getAccounts()).thenReturn(listAccMock);

        //test mockMvc
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("[{\"id\":1,\"name\":\"Dang\",\"gender\":\"male\",\"role\":\"admin\",\"phone\":\"0123\",\"email\":\"dang@mail.com\",\"username\":\"dangdang\",\"password\":\"123456\"}]"));
    }

    @Test
    public void test_getListAccount_giventEmptyList_thenReturnEmptyList() throws Exception {
        List<AccountResponse> listAccMock = new ArrayList<>();;
        Mockito.when(accountService.getAccounts()).thenReturn(listAccMock);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[]"));
    }

    @Test
    public void test_getAccount_givenIdAccount_thenReturnAccountFound() throws Exception {
        Long id = 1L;
        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
        Mockito.when(accountService.getOneAccount(id)).thenReturn(accountResponse1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"status\":0,\"message\":\"Account found with id: 1\",\"data\":{\"id\":1,\"name\":\"Dang\",\"gender\":\"male\",\"role\":\"admin\",\"phone\":\"0123\",\"email\":\"dang@mail.com\",\"username\":\"dangdang\",\"password\":\"123456\"}}"));
    }

    @Test
    public void test_getAccount_giventIdAccount_thenReturnAccountNotFound() throws Exception {
        Long id = 1L;
        Mockito.when(accountService.getOneAccount(id)).thenThrow(new RuntimeException("Not found account with this id: "+id)).toString();
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/api/account/"+id))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"status\":1,\"message\":\"Not found account with this id: 1\",\"data\":\"\"}"))
                .andReturn();
        }
    @Test
    public void test_addAccount_givenNewAccount_thenReturnAccountAdded() throws Exception {
        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
        Mockito.when(accountService.addAccount(any())).thenReturn(accountResponse1);
        String json = "{\"name\":\"user\",\"gender\":\"female\",\"role\":\"ROLE_USER\",\"phone\":\"0395677415\",\"email\":\"user@gmail.com\",\"username\":\"user\",\"password\":\"123456\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account/add").contentType("application/json").content(json).accept("application/json"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"status\":0,\"message\":\"Add successful account\",\"data\":{\"id\":null,\"name\":\"user\",\"gender\":\"female\",\"role\":\"ROLE_USER\",\"phone\":\"0395677415\",\"email\":\"user@gmail.com\",\"username\":\"user\",\"password\":\"123456\"}}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_deleteAccount_givenIdAccount_thenReturnAccountDeleted() throws Exception {
        Long id = 1L;
        Mockito.doNothing().when(accountService).deleteAccount(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/"+id))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"status\":0,\"message\":\"Delete account successfully with id:1\",\"data\":1}"));
    }
    @Test
    public void test_deleteAccount_givenIdAccount_thenReturnAccountNotFound() throws Exception{
        Long id = 1L;
        Mockito.doThrow(new RuntimeException()).when(accountService).deleteAccount(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/"+id))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"status\":1,\"message\":\"Not found account with this id: 1\",\"data\":\"\"}"));
    }
    @Test
    public void test_updateAccount_givenIdAccountToUpdate_thenReturnNotFound() throws Exception {
        Long id = 1L;
        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");

        String json = gson.toJson(accountResponse1);

        MessageResponse messageExpected = new MessageResponse();
        messageExpected.setStatus(1);
        messageExpected.setMessage("Not found account with this id: " + id);
        messageExpected.setData("");
        String expectedContent = gson.toJson(messageExpected);
        //any(kèm eq) không quan tâm địa chỉ vùng nhớ của object
        Mockito.when(accountService.updateAccount(any(AccountResponse.class), eq(id))).thenThrow(new RuntimeException("Not found account with this id: "+ id));
        mockMvc.perform(MockMvcRequestBuilders.put("/api/account/"+id).contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedContent));
    }
    @Test
    public void test_updateAccount_givenAccountUpdate_thenReturnAccountUpdate() throws Exception {
        Long id = 1L;
        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");

        String jsonContent = gson.toJson(accountResponse1);

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setStatus(0);
        messageResponse.setMessage("Update account successfully with id: "+id);
        messageResponse.setData(accountResponse1);
        String expectedContent = gson.toJson(messageResponse);

//        Mockito.when(accountService.updateAccount(accountResponse1,id)).thenReturn(accountResponse1);
        mockMvc.perform(put("/api/account/"+id).contentType(MediaType.APPLICATION_JSON).content(jsonContent).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedContent));
    }

}