package ru.orion.library.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "book")
@Data
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private int ageLowerBound;
    private Integer yearOfIssue;
    private String genre;
    private String author;
    private Integer count;
}
