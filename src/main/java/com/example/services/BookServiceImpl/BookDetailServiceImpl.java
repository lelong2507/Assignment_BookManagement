package com.example.services.BookServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.BookDetails;
import com.example.repository.BookDetailRepository;
import com.example.services.BookDetailService;

@Service
public class BookDetailServiceImpl implements BookDetailService {
    @Autowired
    private BookDetailRepository bookDetailRepository;

    @Override
    public void addBookDetail(BookDetails bookDetails) {
        bookDetailRepository.save(bookDetails);
    }
}
