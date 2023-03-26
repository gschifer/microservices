package com.example.bookservice.service;

import com.example.bookservice.entities.Book;
import com.example.bookservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Book getBookById(Long id) {
        return repository.findById(id).orElseThrow(() ->  new RuntimeException("OIOIOI"));
    }
}
