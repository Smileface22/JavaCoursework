package com.example.butcher_shop.controllers;

import com.example.butcher_shop.databases.CustomUserDetails;
import com.example.butcher_shop.databases.User;
import com.example.butcher_shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping("/reg")
    public String reg(Model model) {
        model.addAttribute("userForm", new User());
        return "reg";
    }
    @PostMapping("/reg")
    public String regUser(@ModelAttribute("userForm") User userForm, Model model){
        userService.addUser(userForm);
        return "redirect:login";
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isAuthenticated = userService.authenticateUser(username, password);
        if (isAuthenticated) {
            return "redirect:account";
        } else {
            model.addAttribute("errorLog", "Неверные учетные данные");
            return "login";
        }
    }
}
