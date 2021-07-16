package ru.orion.library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import ru.orion.library.models.Reservation;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Optional<Reservation> findByAccountIdAndBookId(Long accId,Long bookId);

    boolean existsByBookId(Long bookId);

    @Transactional
    @Modifying
    @ResponseStatus(HttpStatus.OK)
    @Query(nativeQuery = true, value = "DELETE FROM reservation WHERE account_id = ? AND book_id = ?")
    void deleteByAccountIdAndBookId(Long accId,Long bookId);

}
