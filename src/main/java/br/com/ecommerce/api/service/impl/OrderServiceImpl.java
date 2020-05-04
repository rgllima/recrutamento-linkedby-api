package br.com.ecommerce.api.service.impl;

import br.com.ecommerce.api.model.Customer;
import br.com.ecommerce.api.model.Order;
import br.com.ecommerce.api.model.OrderItem;
import br.com.ecommerce.api.repository.OrderRepository;
import br.com.ecommerce.api.service.OrderService;
import br.com.ecommerce.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Order create(Customer customer, List<OrderItem> items, double total) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(items);
        order.setTotal(total);
        this.processOrder(order);
        return orderRepository.save(order);
    }

    private void processOrder(Order order) {
        for (OrderItem orderItem: order.getItems()) {
            productService.decreaseStock(orderItem.getProduct().getId(), orderItem.getQuantity());
        }
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return orderRepository.findByCustomer(customer);
    }
}
