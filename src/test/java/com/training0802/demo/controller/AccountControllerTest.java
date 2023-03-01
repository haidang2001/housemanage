package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.service.mysql.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }
    @Test
    public void test_getListAccount() throws Exception {
        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
//        AccountResponse accountResponse2 = new AccountResponse("Dang2","female","admin","01234","dangg@mail.com","danggdangg","1234567");
//        List<AccountResponse> listAccMock = Arrays.asList(accountResponse1,accountResponse2);
        List<AccountResponse> listAccMock = Arrays.asList(accountResponse1);

        //test mockito
        Mockito.when(accountService.getAccounts()).thenReturn(listAccMock);
//        List<AccountResponse> actualAccountList = accountController.getListAccount();
//        Assertions.assertThat(actualAccountList).isEqualTo(listAccMock);

        //test mockMvc
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("[{\"id\":1,\"name\":\"Dang\",\"gender\":\"male\",\"role\":\"admin\",\"phone\":\"0123\",\"email\":\"dang@mail.com\",\"username\":\"dangdang\",\"password\":\"123456\"}]"));
    }
    @Test
    public void test_getListAccount_empty() throws Exception {
        List<AccountResponse> listAccMock = new ArrayList<>();;
        Mockito.when(accountService.getAccounts()).thenReturn(listAccMock);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[]"));
    }
    @Test
    public void test_getAccount() throws Exception {
        Long id = 1L;
        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
        Mockito.when(accountService.getOneAccount(id)).thenReturn(accountResponse1);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"status\":0,\"message\":\"Account found with id: 1\",\"data\":{\"id\":1,\"name\":\"Dang\",\"gender\":\"male\",\"role\":\"admin\",\"phone\":\"0123\",\"email\":\"dang@mail.com\",\"username\":\"dangdang\",\"password\":\"123456\"}}"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));

        //		mockMvc.perform(MockMvcRequestBuilders.get("/api/account").accept(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.account").exists())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.account[*].accountId").isNotEmpty());
    }
    @Test
    public void test_getAccount_notFound() throws Exception {
        Long id = 1L;
        Mockito.when(accountService.getOneAccount(id)).thenThrow(new RuntimeException("Not found account with this id: "+id)).toString();
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/api/account/"+id))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"status\":1,\"message\":\"Not found account with this id: 1\",\"data\":\"\"}"))
                .andReturn();
//        String actualJson = mvcResult.getResponse().getContentAsString();
//        Assert.assertEquals(Mockito.when(accountService.getOneAccount(id)).thenThrow(new RuntimeException("Not found account with this id: "+id)).toString(),actualJson);
    }
    @Test
    public void test_addAccount() throws Exception {
//        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
//        Mockito.when(accountService.addAccount(accountResponse1)).thenReturn(accountResponse1);
        String json = "{\"name\":\"user\",\"gender\":\"female\",\"role\":\"ROLE_USER\",\"phone\":\"0395677415\",\"email\":\"user@gmail.com\",\"username\":\"user\",\"password\":\"123456\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/account").accept("application/json"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void test_deleteAccount() throws Exception {
        Long id = 2L;
        Mockito.doThrow(new RuntimeException("not found")).when(accountService).deleteAccount(1L);
//        accountService.deleteAccount(id);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/account/"+id)
                        .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void test_updateAccount() throws Exception {
        Long id = 1L;
        AccountResponse accountResponse1 = new AccountResponse(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
        Mockito.when(accountService.updateAccount(accountResponse1,id)).thenReturn(accountResponse1);
        mockMvc.perform(put("/api/account/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(""));
    }

}