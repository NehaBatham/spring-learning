package com.example.utils;

import static org.junit.jupiter.api.Assertions.*;

import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.shared.Utils;

@SpringBootTest
class UtilsTest {

	@Autowired
	Utils utils;

	@BeforeEach
	void setUp() throws Exception {
	
	}
	

	@Test
	void testGenerateUserId() {
		String userId = utils.generateUserId(30);
		assertNotNull(userId);
		assertTrue(userId.length() == 30);
	}
	
	@Test
	void testGenerateUserId_generates_unique_id() {
		String userId1 = utils.generateUserId(30);
		assertNotNull(userId1);
		
		String userId2 = utils.generateUserId(30);
		assertNotNull(userId2);
		
		assertTrue(!userId1.equalsIgnoreCase(userId2));
		
	}

}
