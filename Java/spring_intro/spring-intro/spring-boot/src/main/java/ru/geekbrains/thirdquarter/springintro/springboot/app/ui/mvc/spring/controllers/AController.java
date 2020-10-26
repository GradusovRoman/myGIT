package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.mvc.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.thirdquarter.springintro.springboot.app.dao.ARepository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.AService;

import java.util.Optional;


public abstract class AController<S extends AService<? extends ARepository<E, I>, E, I>, E, I extends Number> {
    protected static final int PAGE_PRODUCT_LIMIT = 15;
    protected final String elementName = getElementName();
    protected final String rootUrl = "/" + elementName + "s";
    protected final String editForm = "/" + elementName + "-edit-form";
    protected S service;

    protected abstract String getElementName();

    @Autowired
    public void setService(S service) {
        this.service = service;
    }

    @GetMapping
    public String showAll(Model model, @RequestParam(value = "page") Optional<Integer> pageNumber) {
        model.addAttribute("page", service.getAll(pageNumber.orElse(0), PAGE_PRODUCT_LIMIT));
        return rootUrl;
    }

    @GetMapping("/show/{id}")
    public String showById(Model model, @PathVariable(value = "id") I id) {
        model.addAttribute(elementName, service.getOrNew(id));
        return editForm;
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable(value = "id") I id) {
        service.delete(id);
        return "redirect:" + rootUrl;
    }

    @PostMapping("/save")
    public String save(E entity) {
        service.save(entity);
        return "redirect:" + rootUrl;
    }
}
