package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.name LIKE %:keyword% OR b.author LIKE %:keyword% OR b.category.name LIKE %:keyword%")
    List<Book> searchBooks(@Param("keyword") String keyword);
}
