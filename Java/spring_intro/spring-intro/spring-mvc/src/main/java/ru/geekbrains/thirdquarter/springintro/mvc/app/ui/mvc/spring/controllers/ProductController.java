package ru.geekbrains.thirdquarter.springintro.mvc.app.ui.mvc.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.ProductService;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities.Product;

import java.util.Optional;

@Controller
public class ProductController {
    private static final int PAGE_PRODUCT_LIMIT = 5;
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllProducts(
            Model model,
            @RequestParam(value = "min", required = false) Integer min,
            @RequestParam(value = "max", required = false) Integer max,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "sortBy", required = false) String sortBY,
            @RequestParam(value = "sort", required = false) String sort
    ) {

        Pageable pageable = (sortBY == null || sortBY.isEmpty()) ?
                PageRequest.of(page.orElse(1) - 1, PAGE_PRODUCT_LIMIT) :
                PageRequest.of(page.orElse(1) - 1, PAGE_PRODUCT_LIMIT,
                        (sort.equals("up") ? Sort.by(sortBY).ascending() : Sort.by(sortBY).descending()));

        if (min == null && max == null) {
            model.addAttribute("page", this.service.getAll(pageable));
        } else {
            model.addAttribute("page", this.service.getAllByPriceFilter(min, max, pageable));
        }

        return "products";
    }


    @GetMapping("/del/{id}")
    public String deleteProduct(@PathVariable long id, Model model) {
        this.service.delById(id);
        return "redirect:/";
    }

    @GetMapping("/show/{id}")
    public String showProduct(@PathVariable long id, Model model) {
        model.addAttribute("product", (id > 0) ? this.service.getById(id) : new Product());
        return "product";
    }

    @PostMapping("/save")
    public String updateProduct(Product product) {
        //TODO проверка введенных данных
        this.service.save(product);
        return "redirect:/";
    }
}
