package com.pbl5.PBL5_Elearning.helper;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	
	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "bmd1eWVudGhpaG9hbmd2YW5uZ3V5ZW50aGlob2FuZ3Zhbm5ndXllbnRoaWhvYW5ndmFu";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;
    
    Gson gson = new Gson();

    // Tạo ra jwt từ thông tin user
    public String generateToken(String data) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//        String data = gson.toJson(user);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                   .setSubject(data)
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                   .signWith(getSignKey(), SignatureAlgorithm.HS256)
                   .compact();
    }

    // Lấy thông tin user từ jwt
    public String decodeToken(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(getSignKey())
                            .parseClaimsJws(token)
                            .getBody();

        return claims.getSubject();
    }

    public boolean validationToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(authToken);
            return true;
        } catch (Exception ex) {
        	return false;
        }         
    }
    
    private Key getSignKey() {
    	byte[] keyByte = Decoders.BASE64.decode(JWT_SECRET);
    	return Keys.hmacShaKeyFor(keyByte);
    }
}
