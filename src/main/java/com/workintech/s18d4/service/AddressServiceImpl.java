package com.workintech.s18d4.service;

import com.workintech.s18d4.repository.AddressRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import com.workintech.s18d4.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepo;
    private final CustomerRepository customerRepo;

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
        db.setNo(address.getNo());
        db.setCity(address.getCity());
        db.setCountry(address.getCountry());
        db.setDescription(address.getDescription());
        return addressRepo.save(db);
    }

    // Address silinirse customer silinmeyecek → ilişkiyi kopar, sonra adresi sil
    @Override @Transactional
    public void delete(Long id) {
        customerRepo.findByAddress_Id(id).ifPresent(c -> {
            c.setAddress(null);
            customerRepo.save(c);
        });
        addressRepo.deleteById(id);
    }
}
