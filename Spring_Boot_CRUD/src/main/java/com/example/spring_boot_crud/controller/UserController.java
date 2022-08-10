package com.example.spring_boot_crud.controller;

import com.example.spring_boot_crud.model.UserPojo;
import com.example.spring_boot_crud.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IuserService iuserService;

    @GetMapping("/")
    public String input() {
        return "input";
    }

    @PostMapping("/add")
    public String add(@RequestParam("username") String username,@RequestParam("password") String password) {
        UserPojo userPojo= new UserPojo();
        userPojo.setPassword(password);
        userPojo.setUsername(username);
     iuserService.add(userPojo);
        return "redirect:/display";
    }
    @GetMapping("/display")
    public String display(Model model) {
        List<UserPojo> list= iuserService.getAll();
        model.addAttribute("list",list);
        return "display";
    }
}
