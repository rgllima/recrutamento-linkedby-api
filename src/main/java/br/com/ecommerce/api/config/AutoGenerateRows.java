package br.com.ecommerce.api.config;

import br.com.ecommerce.api.model.Role;
import br.com.ecommerce.api.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AutoGenerateRows {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        try {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
            System.out.println(role.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
