package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MiddlewareFilter implements Filter {

    private final List<String> publicUrls = List.of(
            "/login",
            "/signup"
    );

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        // Skip token check for public APIs
        if (publicUrls.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        String token = req.getHeader("token");

        // Reject request
        if (token == null || !token.equals("12345")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.setContentType("application/json");
            res.getWriter().write("{\"message\":\"Unauthorized request\"}");
            return;
        }

        // Allow request
        chain.doFilter(request, response);
    }
}
