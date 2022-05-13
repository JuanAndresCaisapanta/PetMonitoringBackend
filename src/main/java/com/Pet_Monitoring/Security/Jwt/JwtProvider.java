package com.Pet_Monitoring.Security.Jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.Pet_Monitoring.Security.Entities.UserPrincipalDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

//Generar token y lo valida
@Component
public class JwtProvider {
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication authentication) {
		UserPrincipalDetails userPrincipalDetails = (UserPrincipalDetails) authentication.getPrincipal();
		// construir token
		return Jwts.builder().setSubject(userPrincipalDetails.getUsername())
				.setIssuedAt(new Date())
				//.setExpiration(new Date(new Date().getTime() + expiration * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();

	}

	public String getEmailFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("token mal formado");
		} catch (UnsupportedJwtException e) {
			logger.error("token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("token vacío");
		} catch (SignatureException e) {
			logger.error("fail en la firma");
		}
		return false;
	}
}
