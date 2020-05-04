package br.com.ecommerce.api.repository;

import br.com.ecommerce.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findById(int id);

    Customer findByEmail(String email);

}
