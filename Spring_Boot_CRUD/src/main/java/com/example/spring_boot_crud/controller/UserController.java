package com.example.spring_boot_crud.controller;

import com.example.spring_boot_crud.model.UserPojo;
import com.example.spring_boot_crud.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IuserService iuserService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/add")
    public String input() {
        return "input";
    }

    @PostMapping("/add")
    public String add(@RequestParam("username") String username, @RequestParam("password")
    String password, @RequestParam("email")
                      String email, @RequestParam("fullname")
                      String fullname, @RequestParam("dayOfbirth")
                      String dayOfbirth) {
        UserPojo userPojo = new UserPojo();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userPojo.setPassword(passwordEncoder.encode(password));
        userPojo.setUsername(username);
        userPojo.setEmail(email);
        userPojo.setFullname(fullname);
        userPojo.setDayOfBirth(dayOfbirth);
        iuserService.add(userPojo);
        return "home";
    }

    @GetMapping("/display")
    public String display(Model model) {
        List<UserPojo> list = iuserService.getAll();
        model.addAttribute("list", list);
        return "display";
    }

    @GetMapping("/delete")
    public String delete(Model model, @RequestParam("id")
    int id) {
        iuserService.delete(id);
        return "redirect:/display";
    }

    @GetMapping(value = {"/", "/home"})
    public String homepage() {
        return "home";
    }

    @PostMapping("/update")
    public String update(@RequestParam("username") String username, @RequestParam("password")
    String password, @RequestParam("email")
                         String email, @RequestParam("fullname")
                         String fullname, @RequestParam("dayOfbirth")
                         String dayOfbirth,
                         @RequestParam("id")
                         int id) {
        UserPojo userPojo = new UserPojo();
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userPojo.setPassword(passwordEncoder.encode(password));
        userPojo.setUsername(username);
        userPojo.setEmail(email);
        userPojo.setFullname(fullname);
        userPojo.setDayOfBirth(dayOfbirth);
        userPojo.setId(id);
        iuserService.add(userPojo);
        return "redirect:/display";
    }

    @GetMapping("/update")
    public String edit(HttpServletRequest request, @RequestParam("id")
    int id) {
        request.setAttribute("id", id);
        return "update";

    }
}
