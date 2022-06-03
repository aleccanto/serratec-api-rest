package br.com.serratec.monitoria.jwt;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.monitoria.model.UserDetailsImpl;
import br.com.serratec.monitoria.util.JWTUtils;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private JWTUtils jwtUtils;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserDetailsImpl dto = new ObjectMapper().readValue(request.getInputStream(), UserDetailsImpl.class);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					dto.getUsername(), dto.getPassword(), Collections.emptyList());

			Authentication res = authenticationManager.authenticate(authenticationToken);
			return res;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
		Long id = ((UserDetailsImpl) authResult.getPrincipal()).getId();
		String token = jwtUtils.generateToken(username, id);
		response.addHeader("Authorization", "Bearer " + token);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Map<String, String> value = new HashMap<>();
		value.put("Authorization", "Bearer " + token);
		response.getWriter().write(new ObjectMapper().writeValueAsString(value));
	}

}
