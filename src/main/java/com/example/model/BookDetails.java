package com.example.model;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "bookDetails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private Double price;
    private Integer numberOfPage;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
