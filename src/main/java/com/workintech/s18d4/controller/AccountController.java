package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Account> findAll() { return accountService.findAll(); }

    @GetMapping("/{id}")
    public Account find(@PathVariable long id) { return accountService.find(id); }

    @PostMapping("/{customerId}")
    public Account save(@PathVariable long customerId, @RequestBody Account account) {
        Customer c = customerService.find(customerId);
        account.setCustomer(c);
        return accountService.save(account);
    }

    @PutMapping("/{customerId}")
    public Account update(@PathVariable long customerId, @RequestBody Account updated) {
        Customer c = customerService.find(customerId);
        Account db = accountService.find(updated.getId());
        db.setAccountName(updated.getAccountName());
        db.setMoneyAmount(updated.getMoneyAmount());
        db.setCustomer(c);
        return accountService.save(db);
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable long id) { return accountService.delete(id); }
}
