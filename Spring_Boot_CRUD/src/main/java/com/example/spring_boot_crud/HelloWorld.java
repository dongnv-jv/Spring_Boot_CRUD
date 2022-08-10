package com.example.spring_boot_crud;

import org.springframework.stereotype.Controller;

@Controller
public class HelloWorld {
    public String hello(){
        return "index";
    }
}
