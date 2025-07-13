package com.main.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.main.Exception.JWTAuthenticationException;
import com.main.service.JWTService;
import com.main.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	private JWTService jwtService; 
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
 
		String authHeader = request.getHeader("Authorization");
		String token  = null; 
		String username = null; 

		try {
			if(authHeader != null && authHeader.startsWith("Bearer ")) {
				token = authHeader.substring(7);
				username = jwtService.extractUsername(token);
			}
			
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
				
				if(jwtService.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = 
							new UsernamePasswordAuthenticationToken(username,null, userDetails.getAuthorities());
					
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
			filterChain.doFilter(request, response);	
		}catch (JWTAuthenticationException e) {
			sendErrorResponse(response, HttpStatus.FORBIDDEN, "Authentication Failed", e.getMessage());
		}catch (Exception e) {
			sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Authentication Error", "Invalid or missing authentication token");
		}
		
		
//		catch (JWTAuthenticationException e) {
//			
//			response.setStatus(HttpStatus.FORBIDDEN.value());
//			response.setContentType("application/json");
//			response.getWriter().write(
//					"{"
//					+ "\"error\": \"Authenticaitn Failed\","
//				    + "\"message\" \"" + e.getMessage() + "\","
//				    + "\"status\": 403"
//				    + "}"
//					);
//		}catch (Exception e) {
//			response.setStatus(HttpStatus.UNAUTHORIZED.value());
//			response.setContentType("application/json");
//			response.getWriter().write(
//		
//					"{"
//					+ "\"error\": \"Authentication Error\","
//					+ "\"message\": \"Invalid or missing authentication token\","
//					+ "\"status\": 401"
//					+ "}"
//					);
//		}
		
	}
	
	private void sendErrorResponse(
			HttpServletResponse response,
			HttpStatus status,
			String error,
			String message
			) throws IOException{
		response.setStatus(status.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String jsonResponse = String.format(
				"{\"error\": \"%s\", \"message\": \"%s\", \"status\": %d",
				error, message,status.value()
				 );
		response.getWriter().write(jsonResponse);
	}
}









