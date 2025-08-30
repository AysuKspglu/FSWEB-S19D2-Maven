package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override public List<Customer> findAll(){ return repo.findAll(); }
    @Override public Customer find(long id){ return repo.findById(id).orElse(null); }
    @Override public Customer findById(Long id){ return repo.findById(id).orElseThrow(); }
    @Override public Customer save(Customer c){ return repo.save(c); }

    @Override
    public Customer delete(Long id) {
        Customer c = repo.findById(id).orElse(null);
        if (c != null) repo.delete(c);
        return c;
    }
}
