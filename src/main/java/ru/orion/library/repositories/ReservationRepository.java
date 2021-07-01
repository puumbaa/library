package ru.orion.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.orion.library.models.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Reservation.ReservationKey> { }
