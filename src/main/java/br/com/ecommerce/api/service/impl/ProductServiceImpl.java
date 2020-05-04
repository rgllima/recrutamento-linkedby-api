package br.com.ecommerce.api.service.impl;

import br.com.ecommerce.api.model.Product;
import br.com.ecommerce.api.repository.ProductRepository;
import br.com.ecommerce.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(String title, String description, double price, String cover, double discount, int stock) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setCover(cover);
        product.setDiscount(discount);
        product.setStock(stock);
        product.setCreatedAt(new Date(new java.util.Date().getTime()));

        if (product.getStock() > 0) {
            product.setAvailable(true);
        }

        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        product.setCreatedAt(new Date(new java.util.Date().getTime()));
        return productRepository.save(product);
    }

    @Override
    public void decreaseStock(int id, int value) {
        Product product = productRepository.findById(id);
        product.setStock(product.getStock() - value);
        productRepository.save(product);
        this.checkAvailability(product);
    }

    private void checkAvailability(Product product){
        if (product.getStock() <= 0) {
            product.setAvailable(false);
            productRepository.save(product);
        }
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
