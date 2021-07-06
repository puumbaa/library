package ru.orion.library.services;


import ru.orion.library.forms.AccountForm;
import ru.orion.library.models.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    void singUp(AccountForm form);
    List<Account> findAll();
    Optional<Account> findById(Long id);
}
