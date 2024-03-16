//package com.training0802.demo.service.mysql;
//
//import com.training0802.demo.dto.AccountResponse;
//import com.training0802.demo.model.mysql.Account;
//import com.training0802.demo.repository.AccountRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class) //junit4
////@ExtendWith(MockitoExtension.class)
//public class AccountServiceImplTest {
//    @InjectMocks //test cái nào dùng injectmocks
//    private AccountServiceImpl accountServiceImp;
//    @Mock
//    private AccountRepository accountRepository;
//    @Mock
//    private ModelMapper modelMapper;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @Before
//    public void initMocks() {
//        MockitoAnnotations.initMocks(this);
//    }
//    @Test
//    public void test_getAccounts_givenTwoAccount_thenReturnTwoAccount() throws Exception {
//        List<Account> listAccMock = Arrays.asList(
//                new Account(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456"),
//                new Account("Dang2","female","admin","01234","dangg@mail.com","danggdangg","1234567")
//        );
//        when(accountRepository.findAll()).thenReturn(listAccMock);
//        List<AccountResponse> listAcc= accountServiceImp.getAccounts();
//        org.assertj.core.api.Assertions.assertThat(listAcc.size()).isEqualTo(listAccMock.size());
//        verify(accountRepository).findAll();
//    }
//    @Test
//    public void test_getAccount_givenID_thenReturnID(){
//        Long id = 1L;
//        //create mock
//        Account accountByMock = new Account(id,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
//        //define behavior repo
//        doReturn(Optional.of(accountByMock)).when(accountRepository).findById(id);
//        //call service
//        AccountResponse accountByService = accountServiceImp.getOneAccount(id);
//        //result
//        assertEquals(accountByMock.getName(),accountByService.getName());
//        //ensure repo is call (optional)
//        verify(accountRepository).findById(id);
//    }
//    @Test
//    public void test_addAccount_givenAccount_thenReturnAccountId(){
//        //create mock
//        Account accountMock = new Account(1L,"haiDang","male","admin","01232","haidang@mail.com","haidangdang","123456");
//        //define behavior repo
//        when(accountRepository.save(accountMock)).thenReturn(accountMock);
//        //call service
//        AccountResponse accountRes = new AccountResponse(1L,"haiDang","male","admin","01232","haidang@mail.com","haidangdang","123456");
//        accountServiceImp.addAccount(accountRes);
//        //result
//        org.assertj.core.api.Assertions.assertThat(accountRes.getId()).isGreaterThan(0);
//        //ensure repo is call (optional)
//    }
//    @Test
//    public void test_deleteAccount_givenId_thenReturnDeleteOnce(){
//
//        //create mock
//        Account accountMock = new Account(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
//        //define behavior repo
//        //call service
//        accountServiceImp.deleteAccount(1L);
//        //result
//        //ensure repo is call (optional)
//        verify(accountRepository,times(1)).deleteById(accountMock.getId());
//    }
//    @Test
//    public void test_updateAccount_givenAccount_thenReturnAccountEmail(){
//        //create mock
//        Long id = 1L;
//        Account accountByMock = new Account(id,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
//        //define behavior repo
//        when(accountRepository.findById(id)).thenReturn(Optional.of(accountByMock));
//        //call service
//        AccountResponse accountRes = new AccountResponse(id,"Dang","male","admin","0123","haidang@mail.com","dangdang","123456");
//        AccountResponse updatedAccount= accountServiceImp.updateAccount(accountRes,id);
//        //result
//        org.assertj.core.api.Assertions.assertThat(updatedAccount.getEmail()).isEqualTo("haidang@mail.com");
//        //ensure repo is call (optional)
//        verify(accountRepository).findById(id);
//    }
//}