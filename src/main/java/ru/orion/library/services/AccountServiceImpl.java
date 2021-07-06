package ru.orion.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.orion.library.enums.AccountRole;
import ru.orion.library.enums.AccountStatus;
import ru.orion.library.forms.AccountForm;
import ru.orion.library.models.Account;
import ru.orion.library.repositories.AccountRepository;
import java.util.List;
import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void singUp(AccountForm form) {
        Account account = Account.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .dateOfBirth(form.getDateOfBirth())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(AccountRole.USER)
                .status(AccountStatus.ACTIVE)
                .build();

        accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }
}
