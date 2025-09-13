package com.crediya.model.creditsummary.ports;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface IIncrementCreditSummaryUseCase {
	
	Mono<Void> execute(BigDecimal amount);
	
}
