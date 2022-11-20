package ru.javamentor.springbootmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springbootmvc.model.Role;
import ru.javamentor.springbootmvc.model.User;
import ru.javamentor.springbootmvc.service.UserService;
import ru.javamentor.springbootmvc.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public String home(Model model) {
//        model.addAttribute("users", userService.listUsers());
////        model.addAttribute("user", userService.findByUsername(userService.findById(user.getId()).getUsername()));
//        return "index";
//    }

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping()
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.listUsers());
        UserDetails user = userService.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("listRoles", userService.listRoles());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    //------------------------------------------------------------------------------------------------------------------

    @GetMapping(value = "/new")
    public String newUser(Model model, Principal principal) {
        model.addAttribute("listRoles", userService.listRoles());
        UserDetails user = userService.loadUserByUsername(principal.getName());
        model.addAttribute("userCurrent", user);
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        List<String> listS = user.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList());
        Set<Role> listR = userService.listByRole(listS);
        user.setRoles(listR);
        userService.add(user);
        return "redirect:/admin";
    }
    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findByUsername(userService.findById(id).getUsername()));
        model.addAttribute("listRoles", userService.listRoles());
        return "edit";
    }

    @PostMapping("edit")
    public String update(@ModelAttribute("user") User user) {
//        if (bindingResult.hasErrors()) {
//            return "edit";
//        }
        List<String> listS = user.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList());
        Set<Role> listR = userService.listByRole(listS);
        user.setRoles(listR);
        userService.update(user);
        return "redirect:/admin";
    }

    //------------------------------------------------------------------------------------------------------------------
    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
