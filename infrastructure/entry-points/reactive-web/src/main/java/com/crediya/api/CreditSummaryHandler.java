package com.crediya.api;

import com.crediya.api.dto.output.CreditSummaryResponse;
import com.crediya.api.mappers.CreditSummaryMapper;
import com.crediya.model.creditsummary.ports.IGetReportCreditSummaryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreditSummaryHandler {
    
    private final IGetReportCreditSummaryUseCase getReportCreditSummaryUseCase;
    
    @SuppressWarnings("unused")
    public Mono<ServerResponse> getCreditSummary(ServerRequest ignoredServerRequest) {
        return getReportCreditSummaryUseCase.execute()
            .doOnSubscribe(subs -> log.trace("Getting credit summary report"))
            .map(CreditSummaryMapper.INSTANCE::toDto)
            .flatMap(summary -> ServerResponse.ok().bodyValue(summary))
            .switchIfEmpty(ServerResponse.ok().bodyValue(new CreditSummaryResponse(0L, BigDecimal.ZERO)))
            .doOnSuccess(resp -> log.trace("Successfully retrieved credit summary report"));
    }
}
