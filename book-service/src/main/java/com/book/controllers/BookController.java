package com.book.controllers;

import com.book.entities.Book;
import com.book.proxy.CambioProxy;
import com.book.services.BookService;
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
    public Book findBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

        String port = String.valueOf(serverProperties.getPort());

        Book book = service.getBookById(id);

        var cambio = proxy.getCambio(book.getPrice(), "USD", currency);

        book.setEnvironment("book port" + port + " Cambio port" + cambio.getEnvironment());
        book.setPrice(cambio.getConvertedValue());
        book.setCurrency(currency);

        return book;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }
}

