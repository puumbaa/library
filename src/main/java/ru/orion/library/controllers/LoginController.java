package ru.orion.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.orion.library.forms.LoginForm;
import ru.orion.library.services.LoginService;
import ru.orion.library.transfer.TokenDto;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping()
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm form){
        return ResponseEntity.ok(loginService.login(form));
    }
}
