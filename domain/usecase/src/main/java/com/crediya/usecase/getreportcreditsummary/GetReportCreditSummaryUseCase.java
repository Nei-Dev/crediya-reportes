package com.crediya.usecase.getreportcreditsummary;

import com.crediya.model.creditsummary.CreditSummary;
import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import com.crediya.model.creditsummary.ports.IGetReportCreditSummaryUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class GetReportCreditSummaryUseCase implements IGetReportCreditSummaryUseCase {
	
	private final CreditSummaryRepository creditSummaryRepository;
	
	@Override
	public Mono<CreditSummary> execute() {
		return creditSummaryRepository.find()
			.switchIfEmpty(Mono.just(CreditSummary.builder()
				.totalApprovedCredits(0L)
				.totalAmountApproved(BigDecimal.ZERO)
				.build())
			);
	}
}
