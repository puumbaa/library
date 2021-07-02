package ru.orion.library.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
@IdClass(Reservation.ReservationKey.class)
public class Reservation {
    @Id
    @Column(name = "account_id")
    private Long accountId;
    @Id
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "date_of_end")
    private LocalDate dateOfEnd;
    @Id
    @Column(name = "is_actual")
    private boolean isActual;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ReservationKey implements Serializable{
        private Long accountId;
        private Long bookId;
        private boolean isActual;
    }
}
