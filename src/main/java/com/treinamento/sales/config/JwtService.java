package com.treinamento.sales.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
 
  private String SECRET_KEY;
  private Integer EXPIRATION;
  
  public JwtService(@Value("${jwt.secret}") String secret_key, @Value("${jwt.expiration}") Integer expiration) {
    this.SECRET_KEY = secret_key;
    this.EXPIRATION = expiration;
  }
  
  public String extractUsername (String jwtToken) {
    return extractClaim(jwtToken, Claims::getSubject);
  }


  public<T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(jwtToken);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }
  
  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts
      .builder()
      .setClaims(extraClaims)
      .setSubject(userDetails.getUsername())
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
      .signWith(getSigninKey(), SignatureAlgorithm.HS256)
      .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }
  
  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String jwtToken) {
    return Jwts
      .parserBuilder()
      .setSigningKey(getSigninKey())
      .build()
      .parseClaimsJws(jwtToken)
      .getBody();
  }

  private Key getSigninKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
