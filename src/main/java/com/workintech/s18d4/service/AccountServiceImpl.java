package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repo;
    private final CustomerRepository customerRepo; // bazı testlerde null olabilir (tek parametreli ctor kullanıldığında)

    @Autowired
    public AccountServiceImpl(AccountRepository repo, CustomerRepository customerRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
    }

    // Bazı testler tek parametreli kurucu çağırıyor
    public AccountServiceImpl(AccountRepository repo) {
        this.repo = repo;
        this.customerRepo = null;
    }

    // Testlerin beklediği kısayollar
    public Account find(long id) { return repo.findById(id).orElse(null); }
    public Account save(Account account) { return repo.save(account); }

    @Override
    public List<Account> findAll() { return repo.findAll(); }

    @Override
    public Account findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account createForCustomer(Long customerId, Account account) {
        Customer c = requireCustomer(customerId);
        account.setCustomer(c);
        return repo.save(account);
    }

    @Override
    public Account updateForCustomer(Long customerId, Account account) {
        Customer c = requireCustomer(customerId);
        Account db = repo.findById(account.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        db.setAccountName(account.getAccountName());
        db.setMoneyAmount(account.getMoneyAmount());
        db.setCustomer(c);
        return repo.save(db);
    }

    // 🆕 Sil ve silinen hesabı döndür (id ile)
    @Override
    public Account delete(Long id) {
        Account existing = repo.findById(id).orElse(null);
        if (existing != null) {
            repo.delete(existing);
        }
        return existing; // test ifadesinde kullanılabilsin
    }

    // 🆕 Sil ve silinen hesabı döndür (Account ile)
    @Override
    public Account delete(Account account) {
        if (account == null) return null;
        Account existing = account;
        if (account.getId() != null) {
            existing = repo.findById(account.getId()).orElse(account);
        }
        repo.delete(existing);
        return existing;
    }

    private Customer requireCustomer(Long id) {
        if (customerRepo == null) {
            throw new IllegalStateException("CustomerRepository is required for this operation in tests.");
        }
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}
