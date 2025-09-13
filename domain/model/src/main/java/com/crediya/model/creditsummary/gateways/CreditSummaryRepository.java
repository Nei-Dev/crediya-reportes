package com.crediya.model.creditsummary.gateways;

import com.crediya.model.creditsummary.CreditSummary;
import reactor.core.publisher.Mono;

public interface CreditSummaryRepository {
	
	Mono<CreditSummary> save(CreditSummary creditSummary);
	
	Mono<CreditSummary> find();
	
}
