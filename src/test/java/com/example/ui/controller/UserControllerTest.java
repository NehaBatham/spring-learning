package com.example.ui.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.service.UserService;
import com.example.shared.dto.UserDto;
import com.example.ui.model.response.UserRest;

@SpringBootTest
class UserControllerTest {
	
	@InjectMocks
	UserController userController = new UserController();
	
	@Mock
	UserService userService;
	
	UserDto userDto;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		userDto = new UserDto();
		
		userDto.setId(12);
		userDto.setUserId("wewefwf");
		userDto.setFirstName("Neha");
		userDto.setLastName("Batham");
		userDto.setPassword("test");
		userDto.setEmail("test@test.com");
		
	}
	

	@Test
	void testGetUser() {
		when(userService.getUserByEmail(anyString())).thenReturn(userDto);
		UserRest userRest = userController.getUser(userDto.getEmail());
		
		assertNotNull(userRest);
		assertEquals(userDto.getEmail(), userRest.getEmail());
	}
	

	@Test
	@Disabled
	void testCreateUser() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testDeleteUser() {
		fail("Not yet implemented");
	}

}
