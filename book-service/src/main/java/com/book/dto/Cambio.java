package com.book.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class Cambio implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String from;

    private String to;

    private Double conversionFactor;

    //    Transient means the attribute will not be persisted at the DB
    private Double convertedValue;

    private String environment;

}