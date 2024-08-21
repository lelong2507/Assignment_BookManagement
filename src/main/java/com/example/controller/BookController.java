package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Book;
import com.example.model.BookDetails;
import com.example.model.Category;
import com.example.services.BookService;
import com.example.services.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @ExceptionHandler(StackOverflowError.class)
    public String handleStackOverflowError() {
        return "redirect:/error";
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }

    @GetMapping("/")
    public String showListBook(Model model) {
        List<Book> bookList = bookService.showBookList();
        System.out.println("Size: " + bookList.size());
        model.addAttribute("bookList", bookList);
        return "index";
    }

    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") int id, Model model) {
        Book book = bookService.findBookById(id);
        List<Category> cateList = categoryService.showListCategory();
        model.addAttribute("book", book);
        model.addAttribute("cateList", cateList);
        return "components/updateBook";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") String id) {

        try {
            int bookId = Integer.parseInt(id);
            System.out.println(bookId);
            bookService.deleteBook(bookId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + id);
        }
        return "redirect:/books/";
    }

    @GetMapping("/create")
    public String showAddPage(Model model) {
        Book book = new Book();

        List<Category> cateList = categoryService.showListCategory();
        model.addAttribute("book", book);
        model.addAttribute("cateList", cateList);
        return "components/addBook";
    }

    @GetMapping("/search")
    public String handleSearchMethod(@RequestParam("keyWords") String keyWord, Model model) {
        List<Book> bookSearchs = bookService.searchBooks(keyWord);
        System.out.println(bookSearchs);
        model.addAttribute("bookList", bookSearchs);
        return "components/updateBook";
    }

    @PostMapping("/create")
    public String handleCreateBook(@Valid Model model, @ModelAttribute("book") Book book, BindingResult result) {
        System.out.println("Book: " + book);

        if (result.hasErrors()) {
        model.addAttribute("cateList", categoryService.showListCategory());
        return "create-book-form";
        }
        book.setBookDetails(book.getBookDetails());
        bookService.addBook(book, book.getBookDetails());

        return "redirect:/books/";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {

        Book existingBook = bookService.findBookById(id);

        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setBookDetails(book.getBookDetails());
        existingBook.setCategory(book.getCategory());

        bookService.updateBook(existingBook, id);

        return "redirect:/books";
    }

}
