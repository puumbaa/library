package ru.orion.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.orion.library.models.Account;
import ru.orion.library.models.Book;
import ru.orion.library.models.Reservation;
import ru.orion.library.repositories.ReservationRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    ReservationRepository reservationRepository;
    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    FileHandler reservationHandler;
    FileHandler extendingHandler;
    FileHandler cancellingHandler;

    {
        try {
            reservationHandler = new FileHandler("src/main/java/ru/orion/library/loggers/reservation.log", true);
            extendingHandler = new FileHandler("src/main/java/ru/orion/library/loggers/extending.log", true);
            cancellingHandler = new FileHandler("src/main/java/ru/orion/library/loggers/canceling.log", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void reserve(Account account, Book book, LocalDate dateOfEnd) {
        Reservation newRes = Reservation.builder()
                .account(account)
                .book(book)
                .dateOfEnd(dateOfEnd)
                .isActual(true)
                .build();
        //logger writing operation
        logger.addHandler(reservationHandler);
        logger.info("User: "
                + account.getId()
                + " reserved " + book.getId()
                + " till data " + dateOfEnd);
        reservationRepository.save(newRes); //db
        account.getReservationList().add(newRes); //oop
        book.getReservationList().add(newRes);
    }


    @Override
    public void cancelReservation(Account account, Book book) {
        for (Reservation res : account.getReservationList()) {
            if (res.getBook().getId().equals(book.getId())) {
                //logger writing operation
                logger.addHandler(cancellingHandler);
                logger.info("User: "
                        + account.getId()
                        + " cancelled reservation FOR:" + book.getId());
                reservationRepository.updateActualById(res.getId());
                account.getReservationList().remove(res);
                book.getReservationList().remove(res);
            }
        }
    }

    @Override
    public void extendReservation(Account account, Book book, LocalDate newDate) {
        for (Reservation res : account.getReservationList()) {
            if (res.getBook().getId().equals(book.getId())) {
                logger.addHandler(extendingHandler);
                logger.info("User: " + account.getId()
                        + " Extended Book: " + book.getId()
                        + " from " + res.getDateOfEnd()
                        + " till " + newDate);
                reservationRepository.updateDateOfEndById(newDate, res.getId());
                res.setDateOfEnd(newDate);
            }
        }
    }
}
