package com.crediya.dynamodb;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;

@SuppressWarnings("LombokSetterMayBeUsed")
@DynamoDbBean
public class CreditSummaryData {

    private String id;
    private Long totalApprovedCredits;
    private BigDecimal totalAmountApproved;
    
    public CreditSummaryData() {
    }
    
    @SuppressWarnings("unused")
    public CreditSummaryData(String id, Long totalApprovedCredits, BigDecimal totalAmountApproved) {
        this.id = id;
        this.totalApprovedCredits = totalApprovedCredits;
        this.totalAmountApproved = totalAmountApproved;
    }
    
    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDbAttribute("totalApprovedCredits")
    public Long getTotalApprovedCredits() {
        return totalApprovedCredits;
    }
    
    public void setTotalApprovedCredits(Long totalApprovedCredits) {
        this.totalApprovedCredits = totalApprovedCredits;
    }
    
    @DynamoDbAttribute("totalAmountApproved")
    public BigDecimal getTotalAmountApproved() {
        return totalAmountApproved;
    }
    
    public void setTotalAmountApproved(BigDecimal totalAmountApproved) {
        this.totalAmountApproved = totalAmountApproved;
    }
    
    @Override
    public String toString() {
        return "CreditSummaryData{" + "id='" + id + '\'' + ", totalApprovedCredits=" + totalApprovedCredits + ", totalAmountApproved=" + totalAmountApproved + '}';
    }
}
