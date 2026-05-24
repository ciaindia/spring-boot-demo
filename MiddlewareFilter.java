package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    private final List<String> publicUrls = List.of(
            "/login",
            "/signup",
            "/api/auth/**",
            "/public/**",
            "/css/**",
            "/js/**",
            "/images/**"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return publicUrls.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, path));
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendUnauthorizedResponse(response, "Missing or invalid token");
            return;
        }

        String token = authHeader.substring(7);

        if (!isValidToken(token)) {
            sendUnauthorizedResponse(response, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidToken(String token) {
        // For demo only
        // In real project, validate JWT here
        return token.equals("12345");
    }

    private void sendUnauthorizedResponse(
            HttpServletResponse response,
            String message
    ) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"" + message + "\"}");
    }
}
