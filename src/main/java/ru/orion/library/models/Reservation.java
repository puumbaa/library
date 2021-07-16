package ru.orion.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity(name = "reservation")
public class Reservation {

    @EmbeddedId
    private ReservationId reservationId = new ReservationId();

    @Column(name = "date_of_end")
    private LocalDate dateOfEnd;

    @ManyToOne()
    @MapsId(value = "accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne()
    @MapsId(value = "bookId")
    @JoinColumn(name = "book_id")
    private Book book;


    public Reservation(LocalDate dateOfEnd, Account account, Book book) {
        this.dateOfEnd = dateOfEnd;
        this.account = account;
        this.book = book;
    }
}

@Embeddable
@Data
@NoArgsConstructor
class ReservationId implements Serializable {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationId that = (ReservationId) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, bookId);
    }

    public ReservationId(Long accountId, Long bookId) {
        this.accountId = accountId;
        this.bookId = bookId;
    }

    private Long accountId;
    private Long bookId;
}
