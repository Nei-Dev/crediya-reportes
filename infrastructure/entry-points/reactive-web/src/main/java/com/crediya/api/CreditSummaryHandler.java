package com.crediya.api;

import com.crediya.api.dto.output.CreditSummaryResponse;
import com.crediya.model.creditsummary.ports.IGetReportCreditSummaryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreditSummaryHandler {
    
    private final IGetReportCreditSummaryUseCase getReportCreditSummaryUseCase;

    public Mono<ServerResponse> getCreditSummary(ServerRequest serverRequest) {
        return getReportCreditSummaryUseCase.execute()
            .flatMap(summary -> ServerResponse.ok().bodyValue(summary))
            .switchIfEmpty(ServerResponse.ok().bodyValue(new CreditSummaryResponse(0L, BigDecimal.ZERO)));
    }
}
