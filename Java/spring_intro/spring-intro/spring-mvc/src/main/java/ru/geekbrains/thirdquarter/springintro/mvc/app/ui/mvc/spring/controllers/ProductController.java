package ru.geekbrains.thirdquarter.springintro.mvc.app.ui.mvc.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.ProductService;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities.Product;

@Controller
public class ProductController {
    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String showAllProducts(Model model) {
        model.addAttribute("products", this.service.getAll());
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
        if (product.getId() > 0) {
            this.service.update(product);
        } else {
            this.service.create(product);
        }
        return "redirect:/";
    }
}
