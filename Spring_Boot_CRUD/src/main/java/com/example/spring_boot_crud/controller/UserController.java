package com.example.spring_boot_crud.controller;

import com.example.spring_boot_crud.model.UserPojo;
import com.example.spring_boot_crud.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes("user")
public class UserController {
    @Autowired
    private IuserService iuserService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @ModelAttribute("user")
    public UserPojo userPojo() {

        return new UserPojo();
    }

    @GetMapping("/add")
    public String input() {
//        UserPojo userPojo = new UserPojo();
//        model.addAttribute("user", userPojo);
        return "input";
    }

    @PostMapping("/add")
    public String add(@Valid  @ModelAttribute("user") UserPojo userPojo, BindingResult bindingResult,Model request) {

//        UserPojo userPojo = (UserPojo) request.getAttribute("user");
        if (bindingResult.hasErrors()) {
            return "input";
        } else

        userPojo.setPassword(passwordEncoder.encode(userPojo.getDummyPassword()));
        iuserService.add(userPojo);
        return "home";
    }

    @GetMapping("/display")
    public String display(Model model, HttpServletRequest request, HttpServletResponse response) {
       Cookie cookie = new Cookie("cookie1", " Đây là cookie");
        List<UserPojo> list = iuserService.getAll();
        request.setAttribute("list", list);
//        model.addAttribute("list", list);
        response.addCookie(cookie);
        return "display";
    }

    @GetMapping("/displaypage")
    public String fillAllPage(@RequestParam(name = "pages", required = false, defaultValue = "0") int pages,
                              @RequestParam(name = "size", required = false, defaultValue = "5") int size,
                              Model model) {
        Page<UserPojo> listPage = iuserService.findAll(pages, size);
        model.addAttribute("listPage", listPage);
        int[] total = new int[listPage.getSize()];
        for (int i = 1; i < listPage.getSize(); i++) {
            total[i] = i;
        }
        model.addAttribute("total", total);
        return "display";
    }

    @PostMapping("/displayByName")
    public String displayByName(Model model, @RequestParam("findname") String name) {
        List<UserPojo> list = iuserService.findByName(name.toUpperCase());
        model.addAttribute("listByName", list);
        return "display";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id")
                         int id, HttpServletRequest request) {
        request.getAttribute("id");
        iuserService.delete(id);
        return "redirect:/display";
    }

    @GetMapping(value = {"/"})
    public String homepage() {
        return "home";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") UserPojo userPojo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "input";
        } else
            userPojo.setPassword(passwordEncoder.encode(userPojo.getDummyPassword()));
        iuserService.add(userPojo);
        return "redirect:/display";
    }

    @GetMapping("/update")
    public String edit(HttpServletRequest request,/* @RequestParam("id")
    int id,*/ Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        UserPojo userPojo = new UserPojo();
        model.addAttribute("user", userPojo);
        request.setAttribute("id", id);
        return "update";
    }
}
