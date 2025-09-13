package com.crediya.model.creditsummary.ports;

import com.crediya.model.creditsummary.CreditSummary;
import reactor.core.publisher.Mono;

public interface IGetReportCreditSummaryUseCase {
	
	Mono<CreditSummary> execute();
	
}
