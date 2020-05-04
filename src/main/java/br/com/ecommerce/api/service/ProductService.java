package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Product;

import java.util.List;

public interface ProductService {

    Product create(String title, String description, double price, String cover, double discount, int stock);

    Product update(Product product);

    void decreaseStock(int id, int value);

    Product findById(int id);

    List<Product> findAll();

}
