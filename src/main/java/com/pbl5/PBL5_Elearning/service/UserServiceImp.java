package com.pbl5.PBL5_Elearning.service;

import java.util.List;
import java.util.Map;

import com.pbl5.PBL5_Elearning.entity.Users;

public interface UserServiceImp {
	public List<Users> findAllUsers();
	public Users findUserByUsername(String username);
	public List<Map<String, ?>> getUserById(int id);

	public Users findById(int id);

	public boolean existUser(String username);

	public boolean existEmail(String email);

	public Users saveNewUser(Users users);

	public List<Map<String, ?>> searchByName(String name);

	public Users updateUser(Users users);
}
