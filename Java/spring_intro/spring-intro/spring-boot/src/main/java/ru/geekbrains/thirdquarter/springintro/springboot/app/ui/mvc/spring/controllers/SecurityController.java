package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.mvc.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.UsersService;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.User;

@Controller
public class SecurityController {
    private final UsersService usersService;

    @Autowired
    public SecurityController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login-form";
    }

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration-form";
    }

    @PostMapping("/registration")
    public String registration(User user) {
        usersService.registration(user);
        return "redirect:/login";
    }
}
