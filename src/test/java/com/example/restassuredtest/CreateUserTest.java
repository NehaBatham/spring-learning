package com.example.restassuredtest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateUserTest {

private final String CONTEXT_PATH="/users";
	
	@BeforeEach
	void setUp() throws Exception {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
	}

	@Test
	final void testCreateUser() {

        Map<String, Object> userDetails = new HashMap<>();
        userDetails.put("firstName", "Neha");
        userDetails.put("lastName", "Batham");
        userDetails.put("email", "neha.batham@globant.com");
        userDetails.put("password", "123");
 	
		Response response = given().
		contentType("application/json").
		accept("application/json").
		body(userDetails).
		when().
		post(CONTEXT_PATH).
		then().
		statusCode(200).
		contentType("application/json").
		extract().
		response();

		String userId = response.jsonPath().getString("userId");
		assertNotNull(userId);
		assertTrue(userId.length() == 30);
		
		String bodyString = response.body().asString();
		try {
			JSONObject responseBodyJson = new JSONObject(bodyString);
			System.out.println(responseBodyJson);
			
		} catch (JSONException e) {
			fail(e.getMessage());
		}
		
	}
	
}
