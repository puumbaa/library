package ru.orion.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.orion.library.models.Reservation;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Reservation.ReservationKey> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "UPDATE reservation SET is_actual = false WHERE account_id = ? AND book_id = ?")
    @ResponseStatus(HttpStatus.OK)
    void updateActualByAccountIdAndBookId(Long accountId,Long bookId);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "UPDATE reservation SET date_of_end =:newDate WHERE account_id =:accountId AND book_id =:bookId")
    @ResponseStatus(HttpStatus.OK)
    void updateDateOfEndByAccountIdAndBookId
            (@Param("accountId") Long accountId,@Param("bookId") Long bookId,@Param("newDate") LocalDate newDate);
}
