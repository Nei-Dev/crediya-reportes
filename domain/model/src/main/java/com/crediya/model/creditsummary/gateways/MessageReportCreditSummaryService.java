package com.crediya.model.creditsummary.gateways;

import com.crediya.model.creditsummary.CreditSummary;
import reactor.core.publisher.Mono;

public interface MessageReportCreditSummaryService {
	
	Mono<String> sendReportCreditSummary(CreditSummary creditSummary);
	
}
