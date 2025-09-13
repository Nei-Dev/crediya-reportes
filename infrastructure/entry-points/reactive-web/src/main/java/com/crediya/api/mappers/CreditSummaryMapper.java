package com.crediya.api.mappers;

import com.crediya.api.dto.output.CreditSummaryResponse;
import com.crediya.model.creditsummary.CreditSummary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditSummaryMapper {
	
	CreditSummaryMapper INSTANCE = Mappers.getMapper(CreditSummaryMapper.class);
	
	CreditSummaryResponse toDto(CreditSummary domain);
	
}
