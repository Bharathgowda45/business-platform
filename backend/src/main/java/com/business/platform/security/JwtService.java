package com.business.platform.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Service
public class JwtService {
 private final SecretKey key; private final long expiration;
 public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration-minutes}") long mins){
   key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); expiration=mins*60_000;
 }
 public String generate(String username,String role){return Jwts.builder().subject(username).claim("role",role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+expiration)).signWith(key).compact();}
 public Claims claims(String token){return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();}
 public boolean valid(String token){try{claims(token);return true;}catch(JwtException|IllegalArgumentException e){return false;}}
}
