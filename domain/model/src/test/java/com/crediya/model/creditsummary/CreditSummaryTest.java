package com.crediya.model.creditsummary;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditSummaryTest {

	@Test
	void increment_increasesCounts() {
		CreditSummary summary = new CreditSummary();
		summary.increment(BigDecimal.valueOf(1000));
		assertEquals(1L, summary.getTotalApprovedCredits());
		assertEquals(0, summary.getTotalAmountApproved().compareTo(BigDecimal.valueOf(1000)));

		summary.increment(BigDecimal.valueOf(2000));
		assertEquals(2L, summary.getTotalApprovedCredits());
		assertEquals(0, summary.getTotalAmountApproved().compareTo(BigDecimal.valueOf(3000)));
	}
	
}
