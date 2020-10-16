package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.ProductService;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestProductController {
    private final ProductService service;

    @Autowired
    public RestProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/show/all")
    public List<Product> showAllProducts() {
        return this.service.getAll();
    }


    @DeleteMapping("/del/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable long id) {
        this.service.delById(id);
    }

    @GetMapping("/show/{id}")
    public Product showProduct(@PathVariable long id) {
        //TODO ошибка если нет.
        return this.service.getById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product product) {
        this.service.save(product);
    }
    //TODO обработка ошибок.
    //TODO обработка ошибок.
    //TODO обработка ошибок.
}
