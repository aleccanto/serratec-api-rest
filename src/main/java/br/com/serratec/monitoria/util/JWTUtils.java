package br.com.serratec.monitoria.util;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.serratec.monitoria.model.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {

	@Value("${jwt.security}")
	private String security;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generationToken(String username) {
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, security.getBytes()).compact();
	}

	public String generateToken(String username, Long id) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", id);
		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + this.expiration))
				.addClaims(claims).signWith(SignatureAlgorithm.HS512, security.getBytes()).compact();
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null && !"".equals(token)) {

			Claims claims = Jwts.parser().setSigningKey("Serratec".getBytes())
					.parseClaimsJws(token.replace("Bearer ", "")).getBody();

			String user = claims.getSubject();
			String id = String.valueOf(claims.get("id"));

			UserDetailsImpl userDetails = new UserDetailsImpl();

			userDetails.setId(Long.parseLong(id));
			userDetails.setUsername(user);

			if (user != null && !"".equals(user)) {
				return new UsernamePasswordAuthenticationToken(userDetails, null, Collections.emptyList());
			}
		}
		return null;
	}

}
