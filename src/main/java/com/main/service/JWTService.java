package com.main.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.main.Exception.JWTAuthenticationException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private static final long TOKEN_VALIDITY = 24 * 60 * 60 * 1000; // 1 day

//	@Value("${jwt.secret:${JWT_SECRET:bXlTZWNyZXRLZXkxMjM0NTY3ODkwMTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTIzNDU2Nzg5MDEyMzQ1Njc4OTA=}}")
	
	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private String jwtExpirationMs;
	
//	public JWTService() {
//		try {
//			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//			SecretKey sk = keyGen.generateKey();
//			secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public String generateToken(String username, String role, Long userId) {
	    Date expiryDate = new Date(System.currentTimeMillis() + TOKEN_VALIDITY);
	    
	    return Jwts.builder()
	            .subject(username)
	            .claim("role", role)
	            .claim("userId", userId)
	            .issuedAt(new Date())
	            .expiration(expiryDate)
	            .signWith(getKey())
	            .compact();
		
//		return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
//				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30)).and().signWith(getKey()).compact();

		 
	}

	private Key getKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}
	public String getRoleFromToken(String token) {
		Claims claims = getClaims(token);
		return claims.get("role",String.class);
	}
	public Long getUserIdFromToken(String token) {
		Claims claims = getClaims(token); 
		return claims.get("Id",Long.class);
	}
	/**
	 * Validates the token against the user details (username).
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		try {
			final String username = extractUsername(token);
			return username.equals(userDetails.getUsername()) && !isTokenExpired(token);	
		}catch (JWTAuthenticationException e) {
			return false;
		}
	}

	/**
	 * Checks if the token is expired.
	 */
	private boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().verifyWith((SecretKey) getKey()).build().parseSignedClaims(token).getPayload();
		} catch (io.jsonwebtoken.security.SignatureException e) {
			throw new JWTAuthenticationException("Invalid JWT signature - tone may be tampered with or expired.", e);
		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			throw new JWTAuthenticationException("JWT token has expired", e);
		} catch (io.jsonwebtoken.MalformedJwtException e) {
			throw new JWTAuthenticationException("Malformed JWT token", e);
		} catch (Exception e) {
			throw new RuntimeException("Invalid JWT token", e);
		}

	}
}
