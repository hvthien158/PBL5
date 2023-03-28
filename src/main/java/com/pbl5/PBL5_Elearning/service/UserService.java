package com.pbl5.PBL5_Elearning.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.repository.UserRepository;

@Service
public class UserService implements UserServiceImp{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Users findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public List<Users> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
}
