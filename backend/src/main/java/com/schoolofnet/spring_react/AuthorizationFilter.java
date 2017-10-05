package com.schoolofnet.spring_react;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter) throws IOException, ServletException {
		
		String authHeader = req.getHeader("Authorization");
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filter.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken auth = validateToken(req);

		SecurityContextHolder.getContext().setAuthentication(auth);
		filter.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken validateToken(HttpServletRequest req) {
		String authHeader = req.getHeader("Authorization");

		if (authHeader == null) {
			return null;
		}
		
		String user = Jwts.parser()
						.setSigningKey("MY_SECRET_KEY_101234564")
						.parseClaimsJws(authHeader.replace("Bearer ", ""))
						.getBody()
						.getSubject();
		
		if (user == null) {
			return null;
		}
		
		return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	}
}
