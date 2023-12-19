package com.travelAgency.travelAgency.jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${jwt.secretKey}")
	private static String secretKey;
	@Value("${jwt.expiration}")
	private long jwtExpiration;
	@Value("${jwt.refresh-token.expiration}")
	private long refreshExpiration;


	public String extractUserName(String jwtToken) {
		return extractClaim(jwtToken,Claims::getSubject);
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

	public String generateToken(UserDetails userDetails){
		return generateToken(new HashMap<>(), userDetails,jwtExpiration);
	}

	public String generateRefreshToken(
		UserDetails userDetails
	) {
		return generateToken(new HashMap<>(), userDetails, refreshExpiration);
	}

	public String generateToken(
		Map<String,Object> extraClaims,
		UserDetails userDetails,
		long jwtExpiration
	){
		return Jwts.builder()
			.setClaims(extraClaims)
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
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
