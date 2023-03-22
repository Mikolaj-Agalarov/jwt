package com.example.controller;

import com.example.entity.UserEntity;
import com.example.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private UserEntityService userEntityService;

    @PostMapping("/newUser")
    public String registerNewUser(UserEntity userEntity) {
        userEntityService.saveUser(userEntity);
        return "redirect:/login/loginUser";
    }

    @GetMapping("/newUser")
    public String sendRegistrationForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "registration";
    }

}
