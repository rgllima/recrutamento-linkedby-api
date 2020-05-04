package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.model.Customer;
import br.com.ecommerce.api.model.Order;
import br.com.ecommerce.api.service.CustomerService;
import br.com.ecommerce.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<Order> create(@RequestBody Order body) {
        Order order = orderService.create(body.getCustomer(), body.getItems(), body.getTotal());
        return ResponseEntity.ok(order);
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Order>> findByCustomer(@PathVariable Integer id) {
        Customer customer = customerService.findById(id);
        return ResponseEntity.ok(orderService.findByCustomer(customer));
    }
}
