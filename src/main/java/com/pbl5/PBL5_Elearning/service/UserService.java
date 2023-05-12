package com.pbl5.PBL5_Elearning.service;


import java.util.List;
import java.util.Map;

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
	public List<Map<String, ?>> getUserById(int id) {
		return userRepository.customFindById(id);
	}

	@Override
	public Users findById(int id) {
		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public boolean existUser(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Users saveNewUser(Users users) {
		return userRepository.saveAndFlush(users);
	}


	@Override
	public List<Users> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
}
