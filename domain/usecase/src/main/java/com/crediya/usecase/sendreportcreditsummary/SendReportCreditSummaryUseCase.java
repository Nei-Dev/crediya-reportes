package com.crediya.usecase.sendreportcreditsummary;

import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import com.crediya.model.creditsummary.gateways.MessageReportCreditSummaryService;
import com.crediya.model.creditsummary.ports.ISendReportCreditSummaryUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SendReportCreditSummaryUseCase implements ISendReportCreditSummaryUseCase {
	
	private final CreditSummaryRepository creditSummaryRepository;
	private final MessageReportCreditSummaryService messageReportCreditSummaryService;
	
	@Override
	public Mono<Void> execute() {
		return creditSummaryRepository.find()
			.flatMap(messageReportCreditSummaryService::sendReportCreditSummary)
			.then();
	}
	
}
