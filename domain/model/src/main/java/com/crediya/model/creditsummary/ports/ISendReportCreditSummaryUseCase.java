package com.crediya.model.creditsummary.ports;

import reactor.core.publisher.Mono;

public interface ISendReportCreditSummaryUseCase {
	
	Mono<Void> execute();
	
}
