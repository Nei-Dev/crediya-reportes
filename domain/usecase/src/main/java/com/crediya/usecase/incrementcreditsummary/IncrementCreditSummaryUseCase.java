package com.crediya.usecase.incrementcreditsummary;

import com.crediya.model.creditsummary.CreditSummary;
import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import com.crediya.model.creditsummary.ports.IIncrementCreditSummaryUseCase;
import com.crediya.usecase.incrementcreditsummary.exceptions.InvalidAmountException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static com.crediya.usecase.incrementcreditsummary.constants.ErrorMessage.INVALID_AMOUNT;

@RequiredArgsConstructor
public class IncrementCreditSummaryUseCase implements IIncrementCreditSummaryUseCase {
	
	private final CreditSummaryRepository creditSummaryRepository;
	
	@Override
	public Mono<Void> execute(BigDecimal amount) {
		return this.validateAmount(amount)
			.flatMap(validatedAmount -> creditSummaryRepository.find()
				.switchIfEmpty(Mono.just(CreditSummary.builder()
					.id(String.valueOf(UUID.randomUUID()))
					.totalApprovedCredits(0L)
					.totalAmountApproved(BigDecimal.ZERO)
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
			.switchIfEmpty(Mono.error(new InvalidAmountException(INVALID_AMOUNT)));
	}
}
