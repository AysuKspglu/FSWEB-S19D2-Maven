package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findById(Long id);
    Account createForCustomer(Long customerId, Account account);
    Account updateForCustomer(Long customerId, Account account);

    // 🆕 Silme işlemi artık silinen hesabı döndürüyor
    Account delete(Long id);

    // 🆕 Eğer test delete(Account) çağırıyorsa onu da karşılayalım
    Account delete(Account account);
}
