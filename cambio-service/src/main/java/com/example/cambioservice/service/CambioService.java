package com.example.cambioservice.service;

import com.example.cambioservice.repository.CambioRepository;
import lombok.AllArgsConstructor;
import com.example.cambioservice.model.Cambio;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@Service
public class CambioService {
    private CambioRepository cambioRepository;

    public Cambio findByFromAndTo(String from, String to) {
        return cambioRepository.findByFromAndTo(from, to)
                .orElseThrow(() -> new RuntimeException("Unsupported Currency"));
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
