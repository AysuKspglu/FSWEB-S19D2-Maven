package com.workintech.s18d4.controller;

import com.workintech.s18d4.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService service;
    public CustomerController(CustomerService service) { this.service = service; }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer c) {
        Customer saved = service.save(c);
        double salary = saved.getSalary() == null ? 0.0 : saved.getSalary();
        return new CustomerResponse(saved.getId(), saved.getEmail(), salary);
    }
}
