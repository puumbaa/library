package ru.orion.library.services;


import ru.orion.library.models.Account;
import ru.orion.library.models.Book;

import java.time.LocalDate;

public interface AccountService {
    void reserve(Account account, Book book, LocalDate dateEnd);
    void cancelReservation(Account account, Book book);
    void extendReservation(Account account, Book book, LocalDate newDate);
}
