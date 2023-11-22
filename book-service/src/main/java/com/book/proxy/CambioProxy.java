package com.book.proxy;

import com.book.dto.Cambio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cambio-microservice")
public interface CambioProxy {
    @GetMapping(value = "/cambios/{amount}/{from}/{to}")
    Cambio getCambio(@PathVariable("amount") Double amount, @PathVariable("from") String from, @PathVariable("to") String to);


}
