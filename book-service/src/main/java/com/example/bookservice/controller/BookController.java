package com.example.bookservice.controller;

import com.example.bookservice.entities.Book;
import com.example.bookservice.proxy.CambioProxy;
import com.example.bookservice.response.Cambio;
import com.example.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
@AllArgsConstructor
public class BookController {

    private ServerProperties serverProperties;
    private BookService service;
    private CambioProxy proxy;

    @Operation(summary = "Find a book by ID.")
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

}
