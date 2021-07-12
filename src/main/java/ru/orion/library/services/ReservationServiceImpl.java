package ru.orion.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.orion.library.enums.BookStatus;
import ru.orion.library.models.Account;
import ru.orion.library.models.Book;
import ru.orion.library.models.Reservation;
import ru.orion.library.repositories.AccountRepository;
import ru.orion.library.repositories.BookRepository;
import ru.orion.library.repositories.ReservationRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AccountRepository accountRepository;

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
    public Optional<Reservation> findByAccountIdAndBookId(Long accId, Long bookId) {
        return reservationRepository.findByAccountIdAndBookId(accId,bookId);
    }

    @Override
    public boolean updateDateOfEnd(Long accId, Long bookId, int cntOfDays) {
        Optional<Reservation> reservationCandidate = reservationRepository.findByAccountIdAndBookId(accId,bookId);
        if (reservationCandidate.isPresent()){
            Reservation reservation = reservationCandidate.get();
            reservation.setDateOfEnd(reservation.getDateOfEnd().plusDays(cntOfDays));
            reservationRepository.save(reservation);
            return true;
        } return false;
    }

    @Override
    public boolean save(Account account, Long bookId) {
        Optional<Book> bookCandidate = bookRepository.findById(bookId);
        if (bookCandidate.isPresent()){

            Book book = bookCandidate.get();
            if (reservationRepository.existsByBookId(bookId)) return false;
//            if (date.equals(LocalDate.now())) date = LocalDate.now().plusDays(7);

            Reservation reservation = Reservation.builder()
                    .account(account)
                    .book(book)
                    .dateOfEnd(LocalDate.now().plusDays(7))
                    .build();

            book.setStatus(BookStatus.RESERVED);
            bookRepository.save(book);

            account.getReservationList().add(reservation);
            accountRepository.save(account);
            return true;

        } return false;
    }

    @Override
    public boolean cancel(Account account, Long bookId) {
        Optional<Book> bookCandidate = bookRepository.findById(bookId);
        if (bookCandidate.isPresent()){
            Optional<Reservation> res = reservationRepository.findByAccountIdAndBookId(account.getId(), bookId);
            if (res.isPresent()){
                account.getReservationList().remove(res.get());
                reservationRepository.save(res.get());

                bookCandidate.get().setStatus(BookStatus.FREE);
                bookCandidate.get().setReservation(null);
                bookRepository.save(bookCandidate.get());

                reservationRepository.delete(res.get());
                return true;
            }
        }throw new IllegalArgumentException("Reservation not found");
    }
}
