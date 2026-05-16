package com.app.controller;

import com.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AuthController {
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/auth")
    public String login(@RequestBody Map<String, String> req) {
        // database connection sql query
        if ("admin".equals(req.get("username")) && "admin@123".equals(req.get("password"))) {
            return jwtUtil.generateToken("admin");
        }
        return "Invalid";
    }

    @GetMapping("/hello")
    public String hello() { return "Hello!"; }

    @GetMapping("/dashboard")
    public String dashboard() { return "Welcome to the Dashboard!"; }
}
