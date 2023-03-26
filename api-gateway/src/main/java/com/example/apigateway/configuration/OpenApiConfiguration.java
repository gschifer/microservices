package com.example.apigateway.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Configuration
public class OpenApiConfiguration {


    public List<GroupedOpenApi> listAll(SwaggerUiConfigParameters configParameters, RouteDefinitionLocator locator) {
        var definitions = Optional.ofNullable(locator.getRouteDefinitions())
                                  .map(defs -> defs.collectList().block())
                                  .orElse(Collections.emptyList());

        definitions.stream()
                   .filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
                   .forEach(getRouteDefinitionConsumer(configParameters));

        return new ArrayList<>();
    }

    private Consumer<RouteDefinition> getRouteDefinitionConsumer(SwaggerUiConfigParameters configParameters) {

        return routeDefinition -> {
            String name = routeDefinition.getId();
            configParameters.addGroup(name);
            GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
        };
    }



}
