package com.training0802.demo;

import com.training0802.demo.controller.AccountController;
import com.training0802.demo.dto.AccountResponse;
import com.training0802.demo.dto.HouseResponse;
import com.training0802.demo.model.mysql.Account;
import com.training0802.demo.model.mysql.House;
import com.training0802.demo.model.mysql.Room;
import com.training0802.demo.repository.AccountRepository;
import com.training0802.demo.repository.MysqlHouseRepository;
import com.training0802.demo.service.HouseService;
import com.training0802.demo.service.mysql.AccountServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class DemoApplicationTests {
	@Autowired
	private AccountServiceImpl accountService;
	@MockBean
	private AccountRepository accountRepository;
	@Autowired
	private HouseService houseService;
	@MockBean
	private MysqlHouseRepository mysqlHouseRepository;
	@Test
	void contextLoads() {
	}
	@Test
	public void getAccountsTest() throws Exception {
		//create mock, define behavior repo
		when(accountRepository.findAll())
				.thenReturn(Stream.of(
								new Account("Dang","male","admin","0123","dang@mail.com","dangdang","123456"),
								new Account("Dang2","female","admin","01234","dangg@mail.com","danggdangg","1234567"))
						.collect(Collectors.toList()));
		//call service, result
		assertEquals(2,accountService.getAccounts().size());
		//ensure repo call
		verify(accountRepository).findAll();
	}
	@Test
	public void getAccountTest(){
		Long id = 1L;
		//create mock
		Account accountByMock = new Account(id,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
		//define behavior repo
		doReturn(Optional.of(accountByMock)).when(accountRepository).findById(id);
		//call service
		AccountResponse accountByService = accountService.getOneAccount(id);
		//result
		assertEquals(id,accountByService.getId());
		assertEquals(accountByMock.getName(),accountByService.getName());
		//ensure repo is call (optional)
		verify(accountRepository).findById(id);
	}
	@Test
	public void addAccountTest(){
		//create mock
		Account accountMock = new Account(1L,"haiDang","male","admin","01232","haidang@mail.com","haidangdang","123456");
		//define behavior repo
		when(accountRepository.save(accountMock)).thenReturn(accountMock);
		//call service
		AccountResponse accountRes = new AccountResponse(1L,"haiDang","male","admin","01232","haidang@mail.com","haidangdang","123456");
		accountService.addAccount(accountRes);
		//result
		Assertions.assertThat(accountRes.getId()).isGreaterThan(0);
		//ensure repo is call (optional)
	}
	@Test
	public void deleteAccountTest(){
		//create mock
		Account accountMock = new Account(1L,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
		//define behavior repo
		//call service
		accountService.deleteAccount(accountMock.getId());
		//result
		//ensure repo is call (optional)
		verify(accountRepository,times(1)).deleteById(accountMock.getId());
	}
	@Test
	public void updateAccountTest(){
		//create mock
		Long id = 1L;
		Account accountByMock = new Account(id,"Dang","male","admin","0123","dang@mail.com","dangdang","123456");
		//define behavior repo
		when(accountRepository.findById(id)).thenReturn(Optional.of(accountByMock));
		//call service
		AccountResponse accountRes = new AccountResponse(id,"Dang","male","admin","0123","haidang@mail.com","dangdang","123456");
		AccountResponse updatedAccount= accountService.updateAccount(accountRes,id);
		//result
		Assertions.assertThat(updatedAccount.getEmail()).isEqualTo("haidang@mail.com");
		//ensure repo is call (optional)
		verify(accountRepository).findById(id);
	}
	@Test
	public void getHousesTest(){
		House house = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
		List<Room> listRoom = Arrays.asList(
				new Room(1L,"Room101",house,4,20,"picture","occupied","cleaning",10000,"room of house"),
				new Room(2L,"Room102",house,3,20,"picture2","occupied","cleaning",10000,"room of house")
		);
		List<House> listHouse = Arrays.asList(
				new House(1L,"Truong chinh","Chinh","02/08/2022","Hai","available","house at TC",listRoom),
				new House(2L,"Truong chinh","Chinh","02/08/2022","Hai","available","house at TC",listRoom)
		);

		when(mysqlHouseRepository.findAll())
				.thenReturn(listHouse);
		//call service, result
		assertEquals(2,houseService.getHouses().size());
		//ensure repo call
		verify(mysqlHouseRepository).findAll();
	}
	@Test
	public void getHouseDetailTest(){
		Long id = 1L;
		//create mock
		House houseByMock = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
		//define behavior repo
		doReturn(Optional.of(houseByMock)).when(mysqlHouseRepository).findById(id);
		//call service
		HouseResponse houseByService = houseService.getHouseDetail(id);
		//result
		assertEquals(id,houseByService.getId());
		assertEquals(houseByMock.getName(),houseByService.getName());
		//ensure repo is call (optional)
		verify(mysqlHouseRepository).findById(id);
	}
	@Test
	public void addHouseTest(){
		//create mock
		House houseByMock = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
		//define behavior repo
		when(mysqlHouseRepository.save(houseByMock)).thenReturn(houseByMock);
		//call service
		HouseResponse houseRes = new HouseResponse(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
		houseService.addHouse(houseRes);
		//result
		Assertions.assertThat(houseRes.getId()).isGreaterThan(0);
		//ensure repo is call (optional)
	}
	@Test
	public void deleteHouseTest(){
		//create mock
		House houseMock = new House(1L,"Truong Chinh","TC house","08/02/2022","Hai","avalable","this is house at TC");
		//call service
		houseService.deleteHouse(houseMock.getId());
		//ensure repo is call (optional)
		verify(mysqlHouseRepository,times(1)).deleteById(houseMock.getId());
	}
}
