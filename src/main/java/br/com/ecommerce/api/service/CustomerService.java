package br.com.ecommerce.api.service;


import br.com.ecommerce.api.model.Customer;
import br.com.ecommerce.api.model.Role;

import java.util.List;

public interface CustomerService {

    Customer create(String name, String email, String password, List<Role> roles);

    Customer findById(int id);

    Customer findByEmail(String email);

}
