package ru.orion.library.repositories;

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

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE reservation SET is_actual = false WHERE id = ?")
    @ResponseStatus(HttpStatus.OK)
    void updateActualById(Long id);




    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE reservation SET date_of_end = ? WHERE id = ?")
    @ResponseStatus(HttpStatus.OK)
    void updateDateOfEndById(LocalDate newDate, Long id);
}
