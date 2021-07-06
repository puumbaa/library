package ru.orion.library.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.orion.library.forms.LoginForm;
import ru.orion.library.models.Account;
import ru.orion.library.models.Token;
import ru.orion.library.repositories.AccountRepository;
import ru.orion.library.repositories.TokenRepository;
import ru.orion.library.transfer.TokenDto;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public TokenDto login(LoginForm form) {
        Optional<Account> accountCandidate = accountRepository.findByEmail(form.getEmail());
        if (accountCandidate.isPresent()){
            Account account = accountCandidate.get();

            if (passwordEncoder.matches(form.getPassword(), account.getHashPassword())){
                Token token = Token.builder()
                        .account(account)
                        .value(RandomStringUtils.random(10,true,true)).build();
                tokenRepository.save(token);
                return TokenDto.from(token);
            }
        } throw new IllegalArgumentException("User not found");
    }
}
