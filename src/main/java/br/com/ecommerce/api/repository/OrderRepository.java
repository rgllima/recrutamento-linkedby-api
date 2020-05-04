package br.com.ecommerce.api.repository;

import br.com.ecommerce.api.model.Customer;
import br.com.ecommerce.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findById(int id);

//    @Query("SELECT new Order (o.id, o.customer, o.items, o.total) FROM EOrder o WHERE o.customer=(:customer_id)")
    List<Order> findByCustomer(Customer customer);
}
