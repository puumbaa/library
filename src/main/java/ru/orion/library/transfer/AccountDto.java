package ru.orion.library.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.orion.library.models.Account;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String login;


    public static AccountDto from(Account account){
        return new AccountDto(account.getId(),account.getLogin());
    }

    public static List<AccountDto> from(List<Account> accounts){
        return accounts
                .stream()
                .map(AccountDto::from)
                .collect(Collectors.toList());
    }
}
