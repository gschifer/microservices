package com.exchange.service;

import com.exchange.entities.Cambio;
import com.exchange.exception.UnsupportedConversionException;
import com.exchange.repository.CambioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class CambioService {
    private CambioRepository cambioRepository;

    public Cambio findByFromAndTo(String from, String to) {
        return cambioRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new UnsupportedConversionException(String.format("The conversion %s to %s doesn't " +
                        "exist yet, a notification was sent to create this conversion, wait a moment and try again.", from, to)));
    }

    public void create() {
        cambioRepository.save(Cambio.builder()
                .id(1L)
                .from("USD")
                .to("BRL")
                .conversionFactor(BigDecimal.valueOf(5.3))
                .convertedValue(BigDecimal.valueOf(10))
                .environment("0")
                .build());
    }

}
