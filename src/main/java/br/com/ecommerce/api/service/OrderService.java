package br.com.ecommerce.api.service;

import br.com.ecommerce.api.model.Customer;
import br.com.ecommerce.api.model.Order;
import br.com.ecommerce.api.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {

    Order create(Customer customer, List<OrderItem> items, double total);

    Order findById(int id);

    List<Order> findByCustomer(Customer customer);

    List<Order> findAll();

}
