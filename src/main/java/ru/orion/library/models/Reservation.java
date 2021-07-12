package ru.orion.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")
public class Reservation {

    @EmbeddedId
    private ReservationId reservationId = new ReservationId();

    @Column(name = "date_of_end")
    private LocalDate dateOfEnd;

    @ManyToOne()
    @MapsId(value = "accountId")
    @JoinColumn(name = "account_id" , nullable = false)
    private Account account;

    @OneToOne
    @MapsId(value = "bookId")
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;


}

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
class ReservationId implements Serializable {

    private Long accountId;
    private Long bookId;
}
