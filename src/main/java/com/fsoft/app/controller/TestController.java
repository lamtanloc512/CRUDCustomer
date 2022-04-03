package com.fsoft.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.app.domain.Customer;
import com.fsoft.app.dto.CustomerDTO;
import com.fsoft.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin(origins = "http://127.0.0.1:8080/*", maxAge = -1)
public class TestController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody(required = true) CustomerDTO customer) {

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/signup")
    private ResponseEntity<?> signup(@RequestBody(required = true) String customerJson)
            throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Customer _customerMapper = mapper.readValue(customerJson, Customer.class);

        Customer newCustomer = customerService.registerCustomer(_customerMapper);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }
}


