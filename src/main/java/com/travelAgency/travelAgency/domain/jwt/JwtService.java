package com.travelAgency.travelAgency.domain.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.travelAgency.travelAgency.domain.user.entity.Users;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;

@Service
public class JwtService {

	private static final String secretKey = "612E3DFC176A8E32A3E59C2AC65D2612E3DFC176A8E32A3E59C2AC65D2";
	public String extractUserName(String jwtToken) {

		return extractClaim(jwtToken, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);

	}

	public Claims extractAllClaims(String jwtToken){
		return Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(jwtToken)
			.getBody();

	}

	public Key getSigningKey() {

		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);

	}

	public String generateToken(Users userDetails){

		return generateToken(new HashMap<>(), userDetails);
	}


	public String generateToken(
		Map<String,Object> extraClaims,
		Users userDetails
	){
		return Jwts.builder()
			.setClaims(extraClaims)
			.setSubject(userDetails.getEmail())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 1000 + 60 * 24))
			.signWith(getSigningKey(),SignatureAlgorithm.HS256)
			.compact();
	}

	public boolean isTokenValid(String jwtToken, UserDetails userDetails){

		final String username = extractUserName(jwtToken);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);

	}

	public boolean isTokenExpired(String jwtToken) {
		return extractExpiration(jwtToken).before(new Date());
	}

	public Date extractExpiration(String jwtToken) {
		return extractClaim(jwtToken, Claims::getExpiration);
	}

}
