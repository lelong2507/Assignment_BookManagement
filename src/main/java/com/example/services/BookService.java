package com.example.services;

import java.util.List;

import com.example.model.Book;
import com.example.model.BookDetails;

public interface BookService {
    void addBook(Book book, BookDetails bookDetails);

    List<Book> showBookList();

    Book updateBook(Book book, int idBook);

    void deleteBook(int idBook);

    Book findBookById(int idBook);
}
