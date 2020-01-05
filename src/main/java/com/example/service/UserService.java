package com.example.service;

//import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.shared.dto.UserDto;

public interface UserService{ //extends UserDetailsService{

 UserDto createUser(UserDto user);
 UserDto updateUser(String email, UserDto user);
 UserDto getUserByUserId(long id);
 UserDto getUserByEmail(String email);
 void deleteUser(String email);
 
}
