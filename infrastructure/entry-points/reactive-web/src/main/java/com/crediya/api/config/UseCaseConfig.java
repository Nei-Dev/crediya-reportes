package com.crediya.api.config;

import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import com.crediya.usecase.getreportcreditsummary.GetReportCreditSummaryUseCase;
import com.crediya.usecase.incrementcreditsummary.IncrementCreditSummaryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
	
	@Bean
	public IncrementCreditSummaryUseCase incrementCreditSummaryUseCase(
		CreditSummaryRepository creditSummaryRepository
	) {
		return new IncrementCreditSummaryUseCase(creditSummaryRepository);
	}
	
	@Bean
	public GetReportCreditSummaryUseCase getReportCreditSummaryUseCase(
		CreditSummaryRepository creditSummaryRepository
	) {
		return new GetReportCreditSummaryUseCase(creditSummaryRepository);
	}
	
}
