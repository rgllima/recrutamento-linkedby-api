package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Product;
import br.com.ecommerce.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> productCreate(@RequestBody Product body) {
        try {
            System.out.println(body.getPrice());
            Product product = productService.create(body.getTitle(), body.getDescription(), body.getPrice(), body.getCover(), body.getDiscount(), body.getStock());
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/product")
    public ResponseEntity<Product> productUpdate(@RequestBody Product body) {
        Product product = productService.update(body);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
