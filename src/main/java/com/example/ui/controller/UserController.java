package com.example.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserRepository;
import com.example.service.UserService;
import com.example.shared.dto.UserDto;
import com.example.ui.model.request.UserDetailsRequestModel;
import com.example.ui.model.response.UserRest;

@RestController
@RequestMapping("users")  //http://localhost:8080/users
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public UserRest getUser(@RequestParam String email) {
		UserRest returnValue = new UserRest();
		UserDto userDto = userService.getUserByEmail(email);
		BeanUtils.copyProperties(userDto, returnValue);
		
		return returnValue;
	}
	
	@PostMapping
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto createdUser = userService.createUser(userDto);
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
	
	
	@PutMapping
	public UserRest updateUser(@RequestParam String email, @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updatedUser = userService.updateUser(email, userDto);
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping
	public OperationStatusModel deleteUser(@RequestParam String email) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName("DELETE");
		userService.deleteUser(email);
		returnValue.setOperationResult("SUCCESS");
		return returnValue;
	}
}
