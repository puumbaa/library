package ru.orion.library.repositories;

import com.sun.istack.internal.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.orion.library.models.Account;
import ru.orion.library.models.Book;
import ru.orion.library.models.Reservation;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Optional<Reservation> findByAccountIdAndBookId(Long accId,Long bookId);

    boolean existsByBookId(Long bookId);

    @Transactional
    @Modifying
    @ResponseStatus(HttpStatus.OK)
    @Query( nativeQuery = true,
            value = "UPDATE reservation SET date_of_end =:newDate WHERE account_id = :accId AND book_id =:bookId")
    boolean updateDateOfEnd(@Param("accId") Long accId, @Param("bookId") Long bookId,@Param("newDate") LocalDate newDate);


}
