package com.emptrack.empTrack.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
		this.jwtUtils = jwtUtils;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = extractToken(request);
		
		if(token != null) {
			try {
				String username = jwtUtils.extractUsername(token);
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				if(jwtUtils.validateToken(token, username)) {
					UsernamePasswordAuthenticationToken auth =
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			} catch (Exception e) {
				setErrorResponse(response, "권한이 없습니다.");
			}
		}
		filterChain.doFilter(request, response);
	}
	
	private String extractToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		return (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
	}
	
    private void setErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = "{\"message\": \"" + message + "\"}";
        response.getWriter().write(jsonResponse);
    }
}
