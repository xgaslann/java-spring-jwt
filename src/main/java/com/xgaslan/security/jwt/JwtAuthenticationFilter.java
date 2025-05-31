package com.xgaslan.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService _jwtService;

    private final UserDetailsService _userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        _jwtService = jwtService;
        _userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extract JWT token from the request header
        var authorizationHeader = request.getHeader("Authorization");


        // If the token is present, validate it and set the authentication in the security context
        if (authorizationHeader != null && !authorizationHeader.isEmpty()) {
            try {
                var jwtToken = extractJwtFromRequest(request);
                if (jwtToken == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is missing or invalid");
                    return;
                }

                var username = _jwtService.getUsernameFromToken(jwtToken);
                if (username == null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is invalid or expired");
                }

                var userDetails = _userDetailsService.loadUserByUsername(username);
                if (userDetails == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
                    return;
                }

                var isTokenValid = _jwtService.validateToken(jwtToken, userDetails);
                if (!isTokenValid) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is invalid");
                    return;
                }

                var isTokenExpired = _jwtService.isTokenExpired(jwtToken);
                if (isTokenExpired) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is expired");
                    return;
                }

                // Set the authentication in the security context
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Validate the token and set authentication (this part would typically involve calling a JwtService)
                // For example, SecurityContextHolder.getContext().setAuthentication(jwtService.validateToken(jwtToken));
            }
            catch (ExpiredJwtException e) {
                // Handle expired token
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token is expired");
                return;
            }
            catch (Exception e) {
                // Handle token validation failure
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }


    public String extractJwtFromRequest(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null && !authHeader.isBlank()) {
            String[] pars = authHeader.split("\\s+");
            if (pars.length == 2 && "Bearer".equalsIgnoreCase(pars[0])) {
                return pars[1].trim(); // Return the JWT token
            } else if (pars.length == 1) {
                return pars[0]; // Return the JWT token without "Bearer"
            }
        }
        return null; // No JWT token found
    }
}
