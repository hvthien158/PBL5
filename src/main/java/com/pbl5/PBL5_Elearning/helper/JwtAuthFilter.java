package com.pbl5.PBL5_Elearning.helper;

import java.io.IOException;

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
				System.out.println("Auth : Đăng nhập thất bại");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error");
		} finally {
			//Cho phép đi tiếp vào đường dẫn đang gọi
			filterChain.doFilter(request, response);
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

