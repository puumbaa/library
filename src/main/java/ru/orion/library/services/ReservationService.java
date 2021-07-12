package ru.orion.library.services;

import ru.orion.library.models.Account;
import ru.orion.library.models.Book;
import ru.orion.library.models.Reservation;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationService {
    Optional<Reservation> findByAccountIdAndBookId(Long accId,Long bookId);
    boolean updateDateOfEnd(Long accId, Long bookId, int cntOfDays);
    boolean save(Account account,Long bookId);
    boolean cancel(Account account, Long bookId);
}
