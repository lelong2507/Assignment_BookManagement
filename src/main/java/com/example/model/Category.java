package com.example.model;


import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private int idCategory;
    @Column(name= "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Book> bookList;
}
