package com.cambio.cambioservice.controllers;


import com.cambio.cambioservice.entities.Cambio;
import com.cambio.cambioservice.services.CambioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor
@RestController
@RequestMapping("/cambios")
public class CambioController {

    private final CambioService cambioService;
    private ServerProperties serverProperties;

    @GetMapping(value = "/{amount}/{from}/{to}")
    public Cambio getCambio(@PathVariable("amount") BigDecimal amount,
                            @PathVariable("from") String from,
                            @PathVariable("to") String to) {

        String port = String.valueOf(serverProperties.getPort());
        Cambio cambio = cambioService.findByFromAndTo(from, to);


        return Cambio.builder()
                .id(1L)
                .from(from)
                .to(to)
                .conversionFactor(cambio.getConversionFactor())
                .convertedValue(cambio.getConversionFactor().multiply(amount).setScale(2, RoundingMode.CEILING))
                .environment(port)
                .build();

    }

}
