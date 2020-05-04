package br.com.ecommerce.api.controller;

import br.com.ecommerce.api.config.JwtTokenProvider;
import br.com.ecommerce.api.model.Customer;
import br.com.ecommerce.api.model.Role;
import br.com.ecommerce.api.repository.CustomerRepository;
import br.com.ecommerce.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            Customer customer = customerRepository.findByEmail(username);

            if (customer == null) return ResponseEntity.notFound().build();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            return ok(generateModel(customer));

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }
    }

    @PostMapping("/login/asAdmin")
    public ResponseEntity<Object> signInAdmin(@RequestBody AuthenticationRequest data) {

        try {
            String username = data.getUsername();
            Customer customer = customerRepository.findByEmail(username);

            if (customer == null) return ResponseEntity.notFound().build();

            // Como só tem uma 'Role', esse if é suficiente
            if (customer.getRoles().size() == 0) return ResponseEntity.status(403).build();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            return ok(generateModel(customer));

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Usuário e/ou senha inválidos");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> signUp(@RequestBody Customer body) {
        String password = new BCryptPasswordEncoder(12).encode(body.getPassword());

        Customer customer = customerService.create(body.getName(), body.getEmail(), password, body.getRoles());
        return ok(generateModel(customer));
    }

    @GetMapping("/check")
    public ResponseEntity<Object> checkUser() {
        return ResponseEntity.status(200).build();
    }

    private Object generateModel(Customer customer) {
        String[] userRoles = customer.getRoles().stream().map(Role::getName).toArray(String[]::new);
        String token = jwtTokenProvider.createToken(customer.getEmail(), userRoles);
        Map<Object, Object> model = new HashMap<>();
        model.put("id", customer.getId());
        model.put("name", customer.getName());
        model.put("token", "Bearer " + token);
        return  model;
    }

}
