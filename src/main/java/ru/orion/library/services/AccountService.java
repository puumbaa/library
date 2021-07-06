package ru.orion.library.services;


import ru.orion.library.forms.AccountForm;
import ru.orion.library.models.Account;
import ru.orion.library.models.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    void reserve(Account account, Book book, LocalDate dateEnd);
    void cancelReservation(Account account, Book book);
    void extendReservation(Account account, Book book, LocalDate newDate);
    void singUp(AccountForm form);
    List<Account> findAll();
    Optional<Account> findById(Long id);
}
