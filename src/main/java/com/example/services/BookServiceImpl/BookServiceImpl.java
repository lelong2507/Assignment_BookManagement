package com.example.services.BookServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Book;
import com.example.model.BookDetails;
import com.example.repository.BookDetailRepository;
import com.example.repository.BookRepository;
import com.example.repository.CategoryRepository;
import com.example.services.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDetailRepository bookDetailsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void addBook(Book book, BookDetails bookDetails) {
        book.setBookDetails(bookDetails);
        bookDetails.setBook(book);

        bookDetailsRepository.save(bookDetails);
        bookRepository.save(book);
    }

    @Override
    public List<Book> showBookList() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    @Override
    public Book updateBook(Book newBook, int idBook) {
        Optional<Book> optionalBook = bookRepository.findById(idBook);
        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            existingBook.setName(newBook.getName());
            existingBook.setAuthor(newBook.getAuthor());

            if (newBook.getCategory() != null) {
                existingBook.setCategory(newBook.getCategory());
            }

            
            if (newBook.getBookDetails() != null) {
                if (existingBook.getBookDetails() == null) {
                    existingBook.setBookDetails(new BookDetails());
                    existingBook.getBookDetails().setBook(existingBook); // Liên kết BookDetails với Book
                }
                existingBook.getBookDetails().setIsbn(newBook.getBookDetails().getIsbn());
                existingBook.getBookDetails().setPrice(newBook.getBookDetails().getPrice());
                existingBook.getBookDetails().setNumberOfPage(newBook.getBookDetails().getNumberOfPage());
                existingBook.getBookDetails().setPublishDate(newBook.getBookDetails().getPublishDate());
            }

           
            return bookRepository.save(existingBook);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBook(int idBook) {
        bookRepository.deleteById(idBook);
    }

    @Override
    public Book findBookById(int idBook) {
        Optional<Book> b = bookRepository.findById(idBook);
        if (b.isPresent()) {
            Book getBook = b.get();
            return getBook;
        }
        return null;
    }

    @Override
    public List<Book> searchBooks(String keyWords) {
        return bookRepository.searchBooks(keyWords);
    }

}
