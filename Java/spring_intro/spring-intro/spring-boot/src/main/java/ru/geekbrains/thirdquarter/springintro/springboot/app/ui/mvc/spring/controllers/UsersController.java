package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.mvc.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.RolesService;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.UsersService;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.User;

@Controller
@RequestMapping("/users")
public class UsersController extends AController<UsersService, User, Long> {
    private RolesService rolesService;

    @Autowired
    public UsersController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @Override
    protected String getElementName() {
        return "user";
    }

    @Override
    @GetMapping("show/{id}")
    public String showById(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("allRole", rolesService.getAll());
        model.addAttribute(elementName, service.getOrNew(id));
        return editForm;
    }
}
