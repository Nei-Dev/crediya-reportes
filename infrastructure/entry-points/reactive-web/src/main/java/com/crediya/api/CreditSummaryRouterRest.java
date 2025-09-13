package com.crediya.api;

import com.crediya.api.config.Path;
import com.crediya.api.openapi.CreditSummaryDocApi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;

@Configuration
@RequiredArgsConstructor
public class CreditSummaryRouterRest {
    
    private final Path path;
    
    @Bean
    public RouterFunction<ServerResponse> routerFunction(CreditSummaryHandler creditSummaryHandler) {
        return route()
            .GET(
                path.getSummary(),
                creditSummaryHandler::getCreditSummary,
                CreditSummaryDocApi::getCreditSummaryDoc
            )
            .build();
    }
}
