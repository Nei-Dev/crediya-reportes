package com.crediya.sqs.listener;

import java.math.BigDecimal;

public record CreditApprovedMessage(
	BigDecimal amount
) {}
