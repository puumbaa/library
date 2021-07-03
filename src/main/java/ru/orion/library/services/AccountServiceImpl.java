package ru.orion.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.orion.library.models.Account;
import ru.orion.library.models.Book;
import ru.orion.library.models.Reservation;
import ru.orion.library.repositories.ReservationRepository;

import java.time.LocalDate;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public void reserve(Account account, Book book, LocalDate dateOfEnd) {
        Reservation newRes = Reservation.builder()
                .account(account)
                .book(book)
                .dateOfEnd(dateOfEnd)
                .isActual(true)
                .build();

       reservationRepository.save(newRes); //db
       account.getReservationList().add(newRes); //oop
       book.getReservationList().add(newRes);
    }


    @Override
    public void cancelReservation(Account account, Book book) {
        for (Reservation res : account.getReservationList()) {
            if (res.getBook().getId().equals(book.getId())) {
                reservationRepository.updateActualById(res.getId());
                account.getReservationList().remove(res);
                book.getReservationList().remove(res);
            }
        }
    }

    @Override
    public void extendReservation(Account account, Book book, LocalDate newDate) {
        for (Reservation res: account.getReservationList()){
            if (res.getBook().getId().equals(book.getId())){
                reservationRepository.updateDateOfEndById(newDate,res.getId());
                res.setDateOfEnd(newDate);
            }
        }
    }
}
