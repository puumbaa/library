package ru.orion.library.services;


import java.time.LocalDate;

public interface AccountService {
    void reserve(Long accountId, Long bookId, LocalDate dateEnd);
    void cancelReservation(Long accountId,Long bookId);
    void extendReservation(Long accountId,Long bookId,LocalDate newDate);
}
