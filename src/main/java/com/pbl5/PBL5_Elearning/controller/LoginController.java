package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.pbl5.PBL5_Elearning.helper.JwtProvider;
import com.pbl5.PBL5_Elearning.payload.UserRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserServiceImp userServiceImp;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("")
	@CrossOrigin
	public ResponseEntity<?> login(@RequestBody UserRequest user){
		System.out.println(user.getUsername() + "," + user.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken = jwtProvider.generateToken(user.getUsername());
		
		return new ResponseEntity<Token>(new Token(jwtToken, "bearer", userServiceImp.findUserByUsername(user.getUsername()), 24 * 60 * 60 * 1000), HttpStatus.OK);
	}

	class Token{
		private String access_token;
		private String token_type;
		private Users data;
		private long expires_in;

		public Token(String access_token, String token_type, Users data, long expires_in) {
			this.access_token = access_token;
			this.token_type = token_type;
			this.data = data;
			this.expires_in = expires_in;
		}

		public String getAccess_token() {
			return access_token;
		}

		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}

		public String getToken_type() {
			return token_type;
		}

		public void setToken_type(String token_type) {
			this.token_type = token_type;
		}

		public Users getData() {
			return data;
		}

		public void setData(Users data) {
			this.data = data;
		}

		public long getExpires_in() {
			return expires_in;
		}

		public void setExpires_in(long expires_in) {
			this.expires_in = expires_in;
		}
	}

}
