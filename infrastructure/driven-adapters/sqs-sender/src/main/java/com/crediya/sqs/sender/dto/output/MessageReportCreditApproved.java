package com.crediya.sqs.sender.dto.output;

import java.math.BigDecimal;

public record MessageReportCreditApproved(
	Long totalApprovedCredits,
	BigDecimal totalAmountApproved
) {}
