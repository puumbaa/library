package ru.orion.library.models;

import lombok.*;
import ru.orion.library.enums.BookStatus;

import javax.persistence.*;

@Data
@Entity(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id", nullable = false)
    private Account account;
}

