package ru.orion.library.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_of_end")
    private LocalDate dateOfEnd;

    @Column(name = "is_actual")
    private boolean isActual;

    @ManyToOne()
    @JoinColumn(name = "account_id" , nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;
}
