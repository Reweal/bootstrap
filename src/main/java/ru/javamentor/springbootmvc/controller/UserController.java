package ru.javamentor.springbootmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootmvc.model.User;
import ru.javamentor.springbootmvc.service.UserService;

import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/user")
    public String print(ModelMap model, Principal principal) {
        User userByName = userService.findByUsername(principal.getName());
        model.addAttribute("user", userByName);
        return "user";
    }


}
