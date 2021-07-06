package ru.orion.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.orion.library.forms.AccountForm;
import ru.orion.library.models.Account;
import ru.orion.library.services.AccountService;
import ru.orion.library.transfer.AccountDto;

import java.util.List;

import static ru.orion.library.transfer.AccountDto.from;

@RestController()
@RequestMapping("/users")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping()
    public List<AccountDto> getAccounts(){
        return from(accountService.findAll());
    }

    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable("id") Long id){
        return from(accountService.findById(id).get());
    }

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody AccountForm form){
        accountService.singUp(form);
        return ResponseEntity.ok().build();
    }


}
