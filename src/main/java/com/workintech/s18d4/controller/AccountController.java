package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workintech/accounts")
public class AccountController {

    private final AccountService service;

    private AccountResponse toResponse(Account a) {
        Long customerId = (a.getCustomer() != null) ? a.getCustomer().getId() : null;
        return new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount(), customerId);
    }

    @GetMapping
    public List<AccountResponse> getAll() {
        return service.findAll().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public AccountResponse getById(@PathVariable Long id) {
        return toResponse(service.findById(id));
    }

    @PostMapping("/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse createForCustomer(@PathVariable Long customerId,
                                             @RequestBody Account account) {
        return toResponse(service.createForCustomer(customerId, account));
    }

    @PutMapping("/{customerId}")
    public AccountResponse updateForCustomer(@PathVariable Long customerId,
                                             @RequestBody Account account) {
        return toResponse(service.updateForCustomer(customerId, account));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
