package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account find(long id);                 // <-- test böyle çağırıyor
    Account findById(Long id);
    Account save(Account account);
    Account createForCustomer(Long customerId, Account account);
    Account updateForCustomer(Long customerId, Account account);
    Account delete(Long id);               // silinen hesabı döndür
    Account delete(Account account);       // opsiyonel
}
