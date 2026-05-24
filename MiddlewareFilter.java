package com.example.demo;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class MiddlewareFilter extends OncePerRequestFilter {

    

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // TODO Auto-generated method stub

        // return super.shouldNotFilter(request);

        String path = request.getRequestURI();

        return path.equals("/login") || path.equals("/signup");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
     
            filterChain.doFilter(request, response);
        
    }

}
