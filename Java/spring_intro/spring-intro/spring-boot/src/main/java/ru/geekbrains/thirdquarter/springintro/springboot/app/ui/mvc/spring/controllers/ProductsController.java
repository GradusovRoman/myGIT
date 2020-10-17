package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.mvc.spring.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.ProductsService;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Product;
import ru.geekbrains.thirdquarter.springintro.springboot.app.ui.mvc.spring.controllers.utils.ParamManager;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController extends AController<ProductsService, Product, Long> {

    @Override
    @GetMapping(params = {"page"})
    public String showAll(Model model, @RequestParam(value = "page") Optional<Integer> pageNumber) {
        model.addAttribute("paramManager", new ParamManager());
        model.addAttribute("page", this.service.getAll(pageNumber.orElse(0), PAGE_PRODUCT_LIMIT));
        return rootUrl;
    }

    @GetMapping
    public String showAll(
            Model model,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "min", required = false) Integer min,
            @RequestParam(value = "max", required = false) Integer max,
            @RequestParam(value = "sortBy", required = false) String sortBY,
            @RequestParam(value = "sort", required = false) String sort
    ) {
        model.addAttribute("paramManager", new ParamManager());
        model.addAttribute("sortBySort", (sortBY == null) ? "not" : sortBY + sort);

        Pageable pageable = (sortBY == null || sortBY.isEmpty()) ?
                PageRequest.of(page.orElse(1) - 1, PAGE_PRODUCT_LIMIT) :
                PageRequest.of(page.orElse(1) - 1, PAGE_PRODUCT_LIMIT,
                        (sort.equals("up") ? Sort.by(sortBY).ascending() : Sort.by(sortBY).descending()));

        if (min == null && max == null) {
            model.addAttribute("page", this.service.getAll(pageable));
        } else {
            model.addAttribute("page", this.service.getAllByPriceFilter(min, max, pageable));
        }

        return rootUrl;
    }

    @Override
    protected String getElementName() {
        return "product";
    }
}
