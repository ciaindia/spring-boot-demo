package com.app.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/calc")
public class MyCalc {

    @GetMapping
    public String addition(@RequestParam int num1, @RequestParam int num2) {
        return "Addition: " + num1 + " + " + num2 + " = " + (num1 + num2);
    }

    @PostMapping
    public String subtraction(@RequestBody Map<String, Integer> body) {
        int n1 = body.getOrDefault("num1", 0);
        int n2 = body.getOrDefault("num2", 0);
        return "Subtraction: " + n1 + " - " + n2 + " = " + (n1 - n2);
    }

    @PutMapping
    public String multiplication(@RequestBody Map<String, Integer> body) {
        int n1 = body.getOrDefault("num1", 0);
        int n2 = body.getOrDefault("num2", 0);
        return "Multiplication: " + n1 + " * " + n2 + " = " + (n1 * n2);
    }

    @DeleteMapping
    public String division(@RequestBody Map<String, Integer> body) {
        int n1 = body.getOrDefault("num1", 0);
        int n2 = body.getOrDefault("num2", 0);
        if (n2 == 0) return "Error: Division by zero";
        return "Division: " + n1 + " / " + n2 + " = " + (n1 / n2);
    }
}
