package com.example.controller;

import com.example.config.jwt.Jwt;
import com.example.entity.UserEntity;
import com.example.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserEntityService userEntityService;
    @Autowired
    private Jwt jwt;

    @PostMapping("/loginUser")
    public String loginUser(UserEntity userEntity, HttpServletResponse response) {
        Optional<UserEntity> user = userEntityService.getByUsernameAndPassword(userEntity);
        if (user.isPresent()) {
            final String token = jwt.generateToken(user.get().getUsername());
            final Cookie cookie = new Cookie("Authorization", token);
            response.addCookie(cookie);
        }
        return "redirect:/users/users";
    }

    @GetMapping("loginUser")
    public String sendLoginForm(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user", userEntity);
        return "login";
    }
}
