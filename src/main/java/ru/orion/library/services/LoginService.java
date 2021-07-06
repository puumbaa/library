package ru.orion.library.services;

import ru.orion.library.forms.LoginForm;
import ru.orion.library.transfer.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm form);
}
