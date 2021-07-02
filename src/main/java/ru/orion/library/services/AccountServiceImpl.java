package ru.orion.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.orion.library.models.Reservation;
import ru.orion.library.repositories.ReservationRepository;

import java.time.LocalDate;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public void reserve(Long accountId, Long bookId, LocalDate dateOfEnd) {
       reservationRepository.save(
               new Reservation(accountId,bookId,dateOfEnd,true));
    }


    @Override
    public void cancelReservation(Long accountId, Long bookId ) {
        reservationRepository.updateActualByAccountIdAndBookId(accountId,bookId);
    }

    @Override
    public void extendReservation(Long accountId, Long bookId, LocalDate newDate) {
        reservationRepository.updateDateOfEndByAccountIdAndBookId(accountId,bookId,newDate);
    }
}
