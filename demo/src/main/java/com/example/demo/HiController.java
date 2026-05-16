package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HiController {
    
    @GetMapping("/hi")
    public String getMethodName(@RequestParam String param) {
        return "Hello " + param;
    }
    

    @GetMapping("/sqr")
    public String getSqr(@RequestParam int num) {
        return "Square of " + num + " is " + (num * num);
    }

    @GetMapping("/cube")
    public String getCube(@RequestParam int num) {
        return "Cube of " + num + " is " + (num * num * num);
    }
    

}
