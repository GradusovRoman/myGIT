package ru.geekbrains.thirdquarter.springintro.mvc.app.ui.mvc.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.ProductService;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities.Product;

@Controller
public class ProductController {
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllProducts(
            Model model,
            @RequestParam(value = "min", required = false) Integer min,
            @RequestParam(value = "max", required = false) Integer max) {
        model.addAttribute("products", this.service.getAll());
        if (min == null && max == null) {
            model.addAttribute("products", this.service.getAll());
        } else {
            model.addAttribute("products", this.service.getAllByPriceFilter(min, max));
        }
        return "products";
    }


    @GetMapping("/del/{id}")
    public String deleteProduct(@PathVariable long id, Model model) {
        this.service.delById(id);
        model.addAttribute("products", this.service.getAll());
        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String showProduct(@PathVariable long id, Model model) {
        model.addAttribute("product", (id >0 ) ? this.service.getById(id) : new Product());
        return "product";
    }

    @PostMapping("/save")
    public String updateProduct(Product product) {
        //TODO проверка введенных данных
        this.service.save(product);
        return "redirect:/";
    }
}
