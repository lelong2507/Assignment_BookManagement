package com.example.model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

    // @NotBlank(message = "ISBN is required")
    private String isbn;
    // @NotNull(message = "Price is required")
    @Column(name = "price")
    private Double price;
    // @NotNull(message = "Number of Pages is required")
    @Column(name = "numberOfPage")
    private Integer numberOfPage;
    // @NotNull(message = "Publish Date is required")
    @Column(name = "publishDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
