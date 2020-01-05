package com.example.service.implementation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.UserRepository;
import com.example.io.entity.UserEntity;
import com.example.shared.Utils;
import com.example.shared.dto.UserDto;

@SpringBootTest
class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	Utils utils;

	String userId = "sfj3hjk";
	UserEntity userEntity;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeEach
	void setUp(TestInfo testInfo, TestReporter testReporter) throws Exception {
		MockitoAnnotations.initMocks(this);

		this.testInfo = testInfo;
		this.testReporter = testReporter;

		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Neha");
		userEntity.setLastName("Batham");
		userEntity.setUserId(userId);

		testReporter.publishEntry("Running: " + testInfo.getDisplayName() + " - " + testInfo.getTestMethod());
	}

	@Test
	@DisplayName("test to create new user")
	void testCreateUser() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);
		when(utils.generateUserId(anyInt())).thenReturn(userId);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		UserDto userDto = new UserDto();
		UserDto createdUser = userService.createUser(userDto);
		String expected = userEntity.getFirstName();
		String actual = createdUser.getFirstName();
		String errorMsgUserNotCreated = "User creation failed!";
		assertNotNull(createdUser);
		assertEquals(expected, actual, errorMsgUserNotCreated);
	}

	@Nested
	class GetUserTest {
		@Test
		@DisplayName("test to get existing user by email id")
		void testGetUserByEmail() {
			when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
			UserDto userDto = userService.getUserByEmail("test@yahoo.com");
			assertNotNull(userDto);
			assertEquals(userEntity.getFirstName(), userDto.getFirstName());
		}

		@Test
		@DisplayName("test to check the proper error is thrown when user is not found")
		void testGetUser_UserNotFoundException() {
			String errorMsg = "Error message is not correct!";
			when(userRepository.findByEmail(anyString())).thenReturn(null);
			assertThrows(NullPointerException.class, () -> {
				userService.getUserByEmail("test@test.com");
			}, () -> errorMsg);
		}

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
