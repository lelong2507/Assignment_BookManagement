package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idBook;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;

  @OneToOne(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private BookDetails bookDetails;
}