package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.mvc.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.RolesService;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Role;

@Controller
@RequestMapping("/roles")
public class RolesController extends AController<RolesService, Role, Long> {
    @Override
    protected String getElementName() {
        return "role";
    }
}
