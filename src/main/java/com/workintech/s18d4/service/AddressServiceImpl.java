package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Address;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.AddressRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepo;
    private final CustomerRepository customerRepo;

    public Address find(Long id) { return addressRepo.findById(id).orElse(null); }
    public Address save(Address address) { return addressRepo.save(address); }

    @Override
    public List<Address> findAll() { return addressRepo.findAll(); }

    @Override
    public Address findById(Long id) {
        return addressRepo.findById(id).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    @Override
    public Address create(Address address) { return addressRepo.save(address); }

    @Override
    public Address update(Long id, Address address) {
        Address db = findById(id);
        db.setStreet(address.getStreet());
        // Integer tabanlı: gelen Long/long da problemsiz (setter overloadları var)
        db.setNo(address.getNo());
        db.setCity(address.getCity());
        db.setCountry(address.getCountry());
        db.setDescription(address.getDescription());
        return addressRepo.save(db);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        customerRepo.findByAddress_Id(id).ifPresent((Customer c) -> {
            c.setAddress(null);
            customerRepo.save(c);
        });
        addressRepo.deleteById(id);
    }
}
