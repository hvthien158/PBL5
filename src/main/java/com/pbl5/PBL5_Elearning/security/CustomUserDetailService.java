package com.pbl5.PBL5_Elearning.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pbl5.PBL5_Elearning.entity.Roles;
import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.service.RoleServiceImp;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	UserServiceImp userServiceImp;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users user = userServiceImp.findUserByUsername(username);
		
		
		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		SimpleGrantedAuthority roleUser;
		if(user.getRoles() == 0) {
			roleUser = new SimpleGrantedAuthority("ROLE_ADMIN");
//			System.out.println("ROLE_ADMIN");
		} else if(user.getRoles() == 1) {
			roleUser = new SimpleGrantedAuthority("ROLE_TEACHER");
//			System.out.println("ROLE_TEACHER");
		} else if(user.getRoles() == 2) {
			roleUser = new SimpleGrantedAuthority("ROLE_USER");
//			System.out.println("ROLE_USER");
		} else {
			roleUser = null;
		}
		
		roles.add(roleUser);


		return new User(user.getUsername(), user.getPassword(), roles);
	}

}
