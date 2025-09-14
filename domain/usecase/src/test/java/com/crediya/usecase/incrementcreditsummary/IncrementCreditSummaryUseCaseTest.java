package com.crediya.usecase.incrementcreditsummary;

import com.crediya.model.creditsummary.CreditSummary;
import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import com.crediya.usecase.incrementcreditsummary.exceptions.InvalidAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static com.crediya.usecase.incrementcreditsummary.constants.ErrorMessage.INVALID_AMOUNT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncrementCreditSummaryUseCaseTest {
	
	@InjectMocks
	private IncrementCreditSummaryUseCase useCase;
	
	@Mock
	private CreditSummaryRepository creditSummaryRepository;
	
	private CreditSummary existingSummary;
	
	@BeforeEach
	void setUp() {
		existingSummary = CreditSummary.builder()
			.id("1")
			.totalApprovedCredits(5L)
			.totalAmountApproved(BigDecimal.valueOf(5000))
			.build();
	}
	
	@Test
	void execute_incrementsCreditSummary() {
		BigDecimal amountToIncrement = BigDecimal.ONE;
		when(creditSummaryRepository.find()).thenReturn(Mono.just(existingSummary));
		existingSummary.increment(amountToIncrement);
		when(creditSummaryRepository.save(existingSummary)).thenReturn(Mono.just(existingSummary));
		
		StepVerifier.create(useCase.execute(amountToIncrement))
			.verifyComplete();
		
	}
	
	@Test
	void execute_createsNewCreditSummaryWhenNoneExists() {
		BigDecimal amountToIncrement = BigDecimal.ONE;
		when(creditSummaryRepository.find()).thenReturn(Mono.empty());
		when(creditSummaryRepository.save(any(CreditSummary.class)))
			.thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
		
		StepVerifier.create(useCase.execute(amountToIncrement))
			.verifyComplete();
	}
	
	@Test
	void execute_invalidAmount_throwsException() {
		BigDecimal invalidAmount = BigDecimal.ZERO;
		
		StepVerifier.create(useCase.execute(invalidAmount))
			.expectErrorMatches(throwable -> throwable instanceof InvalidAmountException &&
				throwable.getMessage().equals(INVALID_AMOUNT))
			.verify();
	}
	
}
