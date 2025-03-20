package com.emptrack.empTrack.config;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private final Key secretKey;
	private final long expirationTime;
	private final JwtParser jwtParser;
	
	public JwtUtils(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") String expiration) {
		this.secretKey = generateSecretKey(secret);
		this.expirationTime = Long.parseLong(expiration);
		this.jwtParser = Jwts.parser().verifyWith((SecretKey) this.secretKey).build();
	}
	
	public String generateToken(String username) {
		return Jwts.builder()
				.signWith(secretKey)
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expirationTime))
				.compact();
	}

	public boolean validateToken(String token, String username) {
		return extractUsername(token).equals(username) && !isTokenExpired(token);
	}
	
	public String extractUsername(String token) {
		return jwtParser.parseSignedClaims(token).getPayload().getSubject();
	}
	
	private boolean isTokenExpired(String token) {
		return jwtParser.parseSignedClaims(token).getPayload().getExpiration().before(new Date());
	}
	
	private Key generateSecretKey(String secret) {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
