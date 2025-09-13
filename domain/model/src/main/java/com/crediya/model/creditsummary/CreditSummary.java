package com.crediya.model.creditsummary;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreditSummary {
	
	private String id;
	private Long totalApprovedCredits;
	private BigDecimal totalAmountApproved;
	
	public void increment(BigDecimal amount) {
		if (this.totalApprovedCredits == null) {
			this.totalApprovedCredits = 0L;
		}
		if (this.totalAmountApproved == null) {
			this.totalAmountApproved = BigDecimal.ZERO;
		}
		this.totalApprovedCredits += 1;
		this.totalAmountApproved = this.totalAmountApproved.add(amount);
	}
	
}
