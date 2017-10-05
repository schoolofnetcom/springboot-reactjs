package com.schoolofnet.spring_react;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserDetailsService userDetailsService;
	
	public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailsService = userDetailsService;
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/users").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(new AuthFilter(authenticationManager()))
			.addFilter(new AuthorizationFilter(authenticationManager()));		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration cors = new CorsConfiguration();
		
		cors.addAllowedHeader("*");
		cors.addAllowedMethod("*");
		cors.addAllowedOrigin("*");
		cors.addExposedHeader("Authorization");
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", cors);
		
		return source;
	}
}
