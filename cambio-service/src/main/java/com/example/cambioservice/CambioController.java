package com.example.cambioservice;

import com.example.cambioservice.service.CambioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import com.example.cambioservice.model.Cambio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Tag(name = "Cambio Service endpoint")
@AllArgsConstructor
@RestController
@RequestMapping("/cambio-service")
public class CambioController {

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private final CambioService cambioService;

    @Operation(summary = "Get conversion of cambios")
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

//    private final CambioService cambioService;

//    @Autowired
//    public com.example.cambioservice.CambioController(CambioService cambioService) {
//        this.cambioService = cambioService;
//    }
//
//    @GetMapping
//    public List<Cambio> getAllCambios() {
//        return cambioService.getAllCambios();
//    }
//
//    @GetMapping("/{id}")
//    public Cambio getCambioById(@PathVariable Long id) {
//        return cambioService.getCambioById(id);
//    }
//
//    @PostMapping
//    public Cambio createCambio(@RequestBody Cambio cambio) {
//        return cambioService.createCambio(cambio);
//    }
//
//    @PutMapping("/{id}")
//    public Cambio updateCambio(@PathVariable Long id, @RequestBody Cambio cambio) {
//        return cambioService.updateCambio(id, cambio);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteCambio(@PathVariable Long id) {
//        cambioService.deleteCambio(id);
//    }
}
