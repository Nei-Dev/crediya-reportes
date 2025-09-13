package com.crediya.dynamodb.mapper;

import com.crediya.dynamodb.CreditSummaryData;
import com.crediya.model.creditsummary.CreditSummary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditSummaryMapper {
	
	CreditSummaryMapper INSTANCE = Mappers.getMapper(CreditSummaryMapper.class);
	
	CreditSummary toDomain(CreditSummaryData data);
	
	CreditSummaryData toData(CreditSummary domain);
	
}
