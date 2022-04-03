package com.fsoft.app.service;

import com.fsoft.app.domain.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomerService extends UserDetailsService {
    public Customer registerCustomer(Customer customer);
}
