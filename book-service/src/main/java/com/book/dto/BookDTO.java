package com.book.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private Date launchDate;
    private Double price;
    private String currency;
    private String environment;
}

