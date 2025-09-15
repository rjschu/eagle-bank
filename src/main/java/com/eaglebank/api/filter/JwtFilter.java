package com.eaglebank.api.filter;

import com.eaglebank.api.dto.response.ErrorMessage;
import com.eaglebank.api.enums.UserRole;
import com.eaglebank.api.service.CustomUserDetailsService;
import com.eaglebank.api.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
public class JwtFilter extends OncePerRequestFilter {


    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;

    public JwtFilter(CustomUserDetailsService customUserDetailsService, ObjectMapper objectMapper) {
        this.customUserDetailsService = customUserDetailsService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.replace("Bearer ", "");
            try {
                String email = JwtService.getUsername(token);
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, List.of(UserRole.USER::getRoleName));
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }catch (SignatureException exception) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setHeader("Content-Type", "application/json");
                ErrorMessage errorMessage = new ErrorMessage("Invalid token");
                response.getWriter().write(objectMapper.writeValueAsString(errorMessage));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
