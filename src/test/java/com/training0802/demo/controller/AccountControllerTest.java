package com.training0802.demo.controller;

import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.service.mysql.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
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
    public void test_getListAccount(){
        AccountResponse accountResponse1 = new AccountResponse("Dang","male","admin","0123","dang@mail.com","dangdang","123456");
        AccountResponse accountResponse2 = new AccountResponse("Dang2","female","admin","01234","dangg@mail.com","danggdangg","1234567");
        List<AccountResponse> listAccMock = Arrays.asList(accountResponse1,accountResponse2);
        Mockito.when(accountService.getAccounts()).thenReturn(listAccMock);
        List<AccountResponse> actualAccountList = accountController.getListAccount();
        Assertions.assertThat(actualAccountList).isEqualTo(listAccMock);
    }
    @Test
    public void test_getAccount() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/account/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));

        //		mockMvc.perform(MockMvcRequestBuilders.get("/api/account").accept(MediaType.APPLICATION_JSON))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.account").exists())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.account[*].accountId").isNotEmpty());

    }

}