package com.cambio.cambioservice.services;

import com.cambio.cambioservice.entities.Cambio;
import com.cambio.cambioservice.exception.UnsupportedConversionException;
import com.cambio.cambioservice.repository.CambioRepository;
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
