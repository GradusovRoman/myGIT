package ru.geekbrains.thirdquarter.springintro.springboot.app.ui.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.ProductsService;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestProductController {
    private final ProductsService service;

    @Autowired
    public RestProductController(ProductsService service) {
        this.service = service;
    }

    @GetMapping("/show/all")
    public List<Product> showAllProducts() {
        return this.service.getAll();
    }


    @DeleteMapping("/del/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable long id) {
        this.service.delete(id);
    }

    @GetMapping("/show/{id}")
    public Product showProduct(@PathVariable long id) {
        //TODO ошибка если нет.
        return this.service.getOrNew(id);
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
