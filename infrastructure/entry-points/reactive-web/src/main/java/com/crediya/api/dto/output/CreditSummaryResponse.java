package com.crediya.api.dto.output;

import java.math.BigDecimal;

public record CreditSummaryResponse(
	Long totalApprovedCredits,
	BigDecimal totalAmountApproved
) {}
