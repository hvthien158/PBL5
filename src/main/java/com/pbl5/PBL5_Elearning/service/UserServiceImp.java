package com.pbl5.PBL5_Elearning.service;

import java.util.List;

import com.pbl5.PBL5_Elearning.entity.Users;

public interface UserServiceImp {
	public List<Users> findAllUsers();
	public Users findUserByUsername(String username);
}
