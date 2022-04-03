package com.fsoft.app.service.impl;

import com.fsoft.app.domain.Customer;
import com.fsoft.app.domain.Role;
import com.fsoft.app.repository.CustomerRepository;
import com.fsoft.app.repository.RoleRepository;
import com.fsoft.app.service.CustomerService;
import com.fsoft.app.utils.ROLES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        Customer _customer = new Customer();
        _customer.setEmail(customer.getEmail());
        _customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        _customer.getRole().add(roleRepository.findByRole(ROLES.CUSTOMER.getRole()));
        return customerRepository.saveAndFlush(_customer);
    }

}
