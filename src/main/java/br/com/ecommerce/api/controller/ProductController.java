package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Product;
import br.com.ecommerce.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/catalog")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> find(@PathVariable Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

}
