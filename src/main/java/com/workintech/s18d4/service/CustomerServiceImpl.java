package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    // === Testlerin beklediği kısayollar / alias metodlar ===
    // MainTest: customerService.find(id)
    public Customer find(long id) {
        return repo.findById(id).orElse(null);
    }

    // MainTest: customerService.save(customer)
    public Customer save(Customer customer) {
        return repo.save(customer);
    }

    // MainTest bazen delete'i ifade içinde kullanabiliyor.
    // Interface'teki delete(Long) 'void' kalmaya devam etsin (controller uyumu için),
    // ama primitive 'long' parametreli overload geri değer döndürsün:
    public Customer delete(long id) {
        Customer existing = repo.findById(id).orElse(null);
        if (existing != null) {
            repo.delete(existing);
        }
        return existing;
    }

    // ================= Interface implementasyonları =================

    @Override
    public List<Customer> findAll() {
        return repo.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Override
    public Customer create(Customer customer) {
        return repo.save(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer db = findById(id);
        db.setFirstName(customer.getFirstName());
        db.setLastName(customer.getLastName());
        db.setEmail(customer.getEmail());
        db.setSalary(customer.getSalary());
        db.setAddress(customer.getAddress());
        return repo.save(db);
    }

    @Override
    public void delete(Long id) {
        // Controller'ların çağırdığı 'void' sürüm (interface gereği)
        repo.deleteById(id);
    }
}
