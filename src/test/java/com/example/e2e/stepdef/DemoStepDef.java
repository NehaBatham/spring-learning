package com.example.e2e.stepdef;


import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class DemoStepDef {
	
	String uri;
	HttpHeaders headers;
	RestTemplate restTemplate;
	ResponseEntity<String> response;
	
	@Given("^I Set POST user service api endpoint$")
	public void setPostEndpoint(){
		uri = "http://localhost:8080/users";
		System.out.println("uri: " +uri);
	}
	 
	@When ("^I set request Headers$")
	public void setHeaders(){
		headers = new HttpHeaders();
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
	}
	
	@When ("^Send a POST HTTP request$")
	public void sendPostRequest(){
        String jsonBody = "{\"firstName\":\"Neha\", \"lastName\":\"Batham\",\"email\":\"neha.b@gmail.com\",\"password\":\"test\"}";
		System.out.println("\n\n" + jsonBody);
		HttpEntity<String>entity = new HttpEntity<String>(jsonBody, headers);		
		restTemplate = new RestTemplate ();
		response = restTemplate.postForEntity(uri, entity, String.class);
	}
		
	@Then ("^I receive valid Response$")
	public void verifyPostResponse(){
		String responseBodyPOST = response.getBody();
        String responseBody = response.getBody().toString();
		System.out.println("responseBody --->" + responseBody);
		Assert.hasText(responseBody,"userId");
		Assert.isTrue(response.getStatusCode()==HttpStatus.OK);
		System.out.println("User is Added successfully");	
	}

}
