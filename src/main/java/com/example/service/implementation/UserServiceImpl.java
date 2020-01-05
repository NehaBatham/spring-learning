package com.example.service.implementation;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.UserRepository;
import com.example.io.entity.UserEntity;
import com.example.service.UserService;
import com.example.shared.Utils;
import com.example.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
	UserRepository userRepository;
    
    @Autowired
    Utils utils;
    
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
		
	@Override
	public UserDto createUser(UserDto user) {
		
		if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Email already exists");
			
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword("7eqyeuhwj"); //(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity storedUserDetails =  userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		return returnValue;
	}

	
	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		UserEntity userEntity=	userRepository.findByEmail(email);
//		if (userEntity == null) throw new UsernameNotFoundException(email);
//		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
//	}

	@Override
	public UserDto getUserByUserId(long id) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null) throw new RuntimeException("User not found!");
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserByEmail(String email) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null) throw new RuntimeException("User not found!");
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}



	@Override
	public UserDto updateUser(String email, UserDto user) {
		//if(userRepository.findByEmail(user.getEmail()) == null) throw new RuntimeException("User doesn't exists!");
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if (userEntity == null) throw new RuntimeException("User doesn't exists!");
		
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
	
		UserEntity updatedUserDetails =  userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, returnValue);
		
		return returnValue;
	}



	@Override
	public void deleteUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if (userEntity == null) throw new RuntimeException("User doesn't exists!");
		
		userRepository.delete(userEntity);
		
		
	}

}
