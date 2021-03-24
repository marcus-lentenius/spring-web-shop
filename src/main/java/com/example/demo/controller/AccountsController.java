package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountsController {

    @Autowired
    private UserService userService;

    @GetMapping("/newUser")
    public String showRegistrationPage(Model model) {

        model.addAttribute("title", "Registration Page");

        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute("user") User user) {

        user.setPassword(userService.encryptedPassword(user));

        userService.save(user);

        return "login";
    }


    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("title","Login Page");

        return "login";
    }
}
