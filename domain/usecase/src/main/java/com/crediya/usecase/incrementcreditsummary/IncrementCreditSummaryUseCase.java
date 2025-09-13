package com.crediya.usecase.incrementcreditsummary;

import com.crediya.model.creditsummary.CreditSummary;
import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import com.crediya.model.creditsummary.ports.IIncrementCreditSummaryUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
public class IncrementCreditSummaryUseCase implements IIncrementCreditSummaryUseCase {
	
	private final CreditSummaryRepository creditSummaryRepository;
	
	@Override
	public Mono<Void> execute(BigDecimal amount) {
		return this.validateAmount(amount)
			.flatMap(validatedAmount -> creditSummaryRepository.find()
				.switchIfEmpty(creditSummaryRepository.save(CreditSummary.builder()
					.id(String.valueOf(UUID.randomUUID()))
					.totalApprovedCredits(1L)
					.totalAmountApproved(amount)
					.build()))
				.flatMap(creditSummary -> {
					creditSummary.increment(amount);
					return creditSummaryRepository.save(creditSummary);
				})
			)
			.then();
	}
	
	private Mono<BigDecimal> validateAmount(BigDecimal amount) {
		return Mono.justOrEmpty(amount)
			.filter(a -> a.compareTo(BigDecimal.ZERO) > 0)
			.switchIfEmpty(Mono.error(new IllegalArgumentException("Amount must be greater than zero")));
	}
}
