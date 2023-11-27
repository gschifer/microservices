package com.book.controller;

import com.book.dto.BookDTO;
import com.book.entity.Book;
import com.book.proxy.CambioProxy;
import com.book.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService service;
    private final ServerProperties serverProperties;
    private CambioProxy proxy;


    @GetMapping(value = "/{id}/{currency}")
    public BookDTO findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
        Book book = service.getBookById(id);

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .launchDate(book.getLaunchDate())
                .price(cambio.getConvertedValue())
                .currency(currency)
                .environment("book port" + serverProperties.getPort() + " Cambio port" + cambio.getEnvironment())
                .build();
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }
}

