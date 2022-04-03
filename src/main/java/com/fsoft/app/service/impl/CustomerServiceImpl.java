package com.fsoft.app.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import com.fsoft.app.domain.Customer;
import com.fsoft.app.repository.CustomerRepository;
import com.fsoft.app.repository.RoleRepository;
import com.fsoft.app.service.CustomerService;
import com.fsoft.app.utils.ROLES;
import com.fsoft.app.utils.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final RoleRepository roleRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, RoleRepository roleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Customer> _customerFromDB = customerRepository.findByEmail(email);
        Collection<SimpleGrantedAuthority> authories = new ArrayList<>();
        Customer customer = _customerFromDB.get();

        if (_customerFromDB.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        } else {
            customer.getRole()
                    .forEach(role -> authories.add(new SimpleGrantedAuthority(role.getRole())));
        }
        return new UserPrincipal(customer.getEmail(), customer.getPassword(), authories);

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
