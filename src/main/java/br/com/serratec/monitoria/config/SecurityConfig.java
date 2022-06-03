package br.com.serratec.monitoria.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import br.com.serratec.monitoria.jwt.JWTAuthenticationFilter;
import br.com.serratec.monitoria.jwt.JWTAuthorizationFilter;
import br.com.serratec.monitoria.service.AuthService;
import br.com.serratec.monitoria.util.JWTUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthService authService;

	@Autowired
	private JWTUtils jwtUtils;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.cors().configurationSource(request -> corsConfiguration());

		http.authorizeHttpRequests().antMatchers(HttpMethod.POST, "/pessoa/cadastrar").permitAll()
				.antMatchers("/", "/h2-conosle/**").permitAll().anyRequest().authenticated();	

		http.addFilterBefore(new JWTAuthenticationFilter(authenticationManager(), jwtUtils),
				UsernamePasswordAuthenticationFilter.class);

		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.headers().frameOptions().disable();
	}

	private CorsConfiguration corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:5000"));
		return configuration;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder());
	}

}
