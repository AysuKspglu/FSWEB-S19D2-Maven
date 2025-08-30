package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;
    private final CustomerRepository customerRepo;

    public AccountServiceImpl(AccountRepository repo, CustomerRepository customerRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
    }
    // bazı testler tek parametreli ctor kullanıyor
    public AccountServiceImpl(AccountRepository repo) {
        this.repo = repo;
        this.customerRepo = null;
    }

    @Override public List<Account> findAll() { return repo.findAll(); }
    @Override public Account find(long id) { return repo.findById(id).orElse(null); }
    @Override public Account findById(Long id){ return repo.findById(id).orElseThrow(); }
    @Override public Account save(Account a){ return repo.save(a); }

    @Override
    public Account createForCustomer(Long customerId, Account account) {
        Customer c = requireCustomer(customerId);
        account.setCustomer(c);
        return repo.save(account);
    }

    @Override
    public Account updateForCustomer(Long customerId, Account account) {
        Customer c = requireCustomer(customerId);
        Account db = repo.findById(account.getId()).orElseThrow();
        db.setAccountName(account.getAccountName());
        db.setMoneyAmount(account.getMoneyAmount());
        db.setCustomer(c);
        return repo.save(db);
    }

    @Override
    public Account delete(Long id) {
        Account existing = repo.findById(id).orElse(null);
        if (existing != null) repo.delete(existing);
        return existing;
    }

    @Override
    public Account delete(Account account) {
        if (account == null) return null;
        Account existing = (account.getId() > 0) ? repo.findById(account.getId()).orElse(account) : account;
        repo.delete(existing);
        return existing;
    }

    private Customer requireCustomer(Long id) {
        if (customerRepo == null)
            throw new IllegalStateException("CustomerRepository required in this test path");
        return customerRepo.findById(id).orElseThrow();
    }
}
