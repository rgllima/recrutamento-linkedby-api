package br.com.ecommerce.api.service.impl;

import br.com.ecommerce.api.model.Customer;
import br.com.ecommerce.api.model.Role;
import br.com.ecommerce.api.repository.CustomerRepository;
import br.com.ecommerce.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(String name, String email, String password, List<Role> roles) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setRoles(roles);
        customer.setAvailable(true);

        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

}
