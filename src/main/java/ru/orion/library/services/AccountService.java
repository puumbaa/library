package ru.orion.library.services;

import ru.orion.library.models.Account;
import ru.orion.library.models.Book;

import java.time.LocalDate;

public interface AccountService {
    void reserve(Long accountId, Long bookId, LocalDate dateEnd);
    void cancelReservation(Long accountId,Long bookId);
}
