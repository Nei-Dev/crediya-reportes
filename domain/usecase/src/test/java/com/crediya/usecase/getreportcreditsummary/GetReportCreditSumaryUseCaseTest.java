package com.crediya.usecase.getreportcreditsummary;

import com.crediya.model.creditsummary.CreditSummary;
import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetReportCreditSumaryUseCaseTest {
	
	@InjectMocks
	private GetReportCreditSummaryUseCase useCase;
	
	@Mock
	private CreditSummaryRepository creditSummaryRepository;
	
	@Test
	void execute_returnsCreditSummary() {
		CreditSummary summary = CreditSummary.builder()
			.id("1")
			.totalApprovedCredits(5L)
			.totalAmountApproved(java.math.BigDecimal.valueOf(5000))
			.build();
		
		when(creditSummaryRepository.find()).thenReturn(Mono.just(summary));
		
		StepVerifier.create(useCase.execute())
			.expectNext(summary)
			.verifyComplete();
	}
	
	@Test
	void execute_returnsDefaultWhenNoSummary() {
		when(creditSummaryRepository.find()).thenReturn(Mono.empty());
		
		StepVerifier.create(useCase.execute())
			.expectNextMatches(summary -> summary.getTotalApprovedCredits() == 0L &&
				summary.getTotalAmountApproved().compareTo(java.math.BigDecimal.ZERO) == 0)
			.verifyComplete();
	}
	
}
