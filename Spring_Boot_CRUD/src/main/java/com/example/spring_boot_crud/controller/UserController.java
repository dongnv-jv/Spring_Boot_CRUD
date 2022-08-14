package com.example.spring_boot_crud.controller;

import com.example.spring_boot_crud.model.UserPojo;
import com.example.spring_boot_crud.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IuserService iuserService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/add")
    public String input(Model model) {
        UserPojo userPojo = new UserPojo();
        model.addAttribute("user", userPojo);
        return "input";
    }
    @PostMapping("/add")
    public String add(@Valid  @ModelAttribute("user") UserPojo userPojo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "input";
        } else
        userPojo.setPassword(passwordEncoder.encode(userPojo.getDummyPassword()));
        iuserService.add(userPojo);
        return "home";
    }
    @GetMapping("/display")
    public String display(Model model) {
        List<UserPojo> list = iuserService.getAll();
        model.addAttribute("list", list);
        return "display";
    }

//    @GetMapping("/displayByName")
//    public String displayByName(Model model) {
//        List<UserPojo> list = iuserService.findByName();
//        model.addAttribute("listByName", list);
//        return "display";
//    }

    @PostMapping("/displayByName")
    public String displayByName(Model model,@RequestParam("findname") String name) {
        List<UserPojo> list = iuserService.findByName(name);
        model.addAttribute("listByName", list);
        return "display";
    }
    @GetMapping("/delete")
    public String delete( @RequestParam("id")
    int id) {
        iuserService.delete(id);
        return "redirect:/display";
    }
    @GetMapping(value = {"/"})
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
