package ru.orion.library.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.orion.library.enums.BookStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity()
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "reservation")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private BookStatus status;
    @Column(name = "age_lower_bound")
    private int ageLowerBound;
    @Column(name = "year_of_issue")
    private Integer yearOfIssue;
    @Column(name = "genre")
    private String genre;
    @Column(name = "author")
    private String author;


    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    private Reservation reservation;
}

