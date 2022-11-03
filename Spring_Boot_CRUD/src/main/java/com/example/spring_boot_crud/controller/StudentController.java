package com.example.spring_boot_crud.controller;

import com.example.spring_boot_crud.model.UserPojo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
//@SessionAttributes("user")
public class StudentController {

    @GetMapping("/student")
    public String display(/*@SessionAttribute("user") UserPojo userPojo,*/ Model model, RedirectAttributes redirectAttrs) {
//        model.addAttribute("user","toi là Đồng");
//        System.out.println(userPojo.getUsername());
        redirectAttrs.addFlashAttribute("user2","toi là Đồng");
        return "redirect:/home2";
    }
    @GetMapping("/home2")
    public String display2() {
        return "home2";
    }


}
