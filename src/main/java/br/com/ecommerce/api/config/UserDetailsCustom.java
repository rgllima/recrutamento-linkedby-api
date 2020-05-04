package br.com.ecommerce.api.config;

import br.com.ecommerce.api.model.Customer;
import br.com.ecommerce.api.model.Role;
import br.com.ecommerce.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsCustom implements UserDetailsService {

    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerService.findByEmail(email);

        if (customer == null) {
            throw new UsernameNotFoundException("Usuário inválido!");
        } else {
            return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), getAuthorities(customer));
        }
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Customer user) {
        String[] userRoles = user.getRoles().stream().map(Role::getName).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }
}
