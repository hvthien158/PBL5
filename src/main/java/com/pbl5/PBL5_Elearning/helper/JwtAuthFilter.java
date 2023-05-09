package com.pbl5.PBL5_Elearning.helper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pbl5.PBL5_Elearning.security.CustomUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	CustomUserDetailService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
//	private Gson gson = new Gson();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getServletPath().equals("/login")
			|| request.getServletPath().equals("/blog/all")
			|| request.getServletPath().equals("/course/all")
			|| request.getServletPath().equals("/me")){
			filterChain.doFilter(request, response);
		} else {
			try {
				String token = getJwtToken(request);

				if (jwtProvider.validationToken(token)) {
					//Token hợp lệ
					String jsonData = jwtProvider.decodeToken(token);
					System.out.println("Kiemtra data token: " + jsonData);

//				User user = gson.fromJson(jsonData, User.class);
					User userDetail = (User) userService.loadUserByUsername(jsonData);
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
					System.out.println(userDetail.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//				Authentication authentication = authenticationManager.authenticate(authenticationToken);
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);

					//Gọi lại hàm đăng nhập mặc định của Spring Security
				}else {
					//Token không phải do hệ thống sinh ra
					System.out.println("Auth Error");
				}
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				response.setHeader("error", e.getMessage());
				response.setStatus(FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", e.getMessage());
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}

	}

	private String getJwtToken(HttpServletRequest request) {
		String authenToken = request.getHeader("Authorization");
		if (StringUtils.hasText(authenToken) && authenToken.contains("Bearer")) {
			//Loại bỏ Bearer và lấy phần token phía sau Bearer
			String token = authenToken.substring(7);
			return token;
		}
		return null;
	}
}

