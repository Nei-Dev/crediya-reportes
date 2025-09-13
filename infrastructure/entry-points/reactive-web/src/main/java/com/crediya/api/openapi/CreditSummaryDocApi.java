package com.crediya.api.openapi;

import com.crediya.api.dto.output.ApiResponse;
import com.crediya.api.helpers.ApiDocHelper;
import lombok.experimental.UtilityClass;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;

@UtilityClass
public class CreditSummaryDocApi {
	
	public static final String TAG_CREDIT_SUMMARY = "Credit Summary";
	
	// Get Credit Summary
	public static final String OPERATION_ID_GET = "getCreditSummaryDoc";
	public static final String SUMMARY_GET = "Get Credit Summary";
	
	public void getCreditSummaryDoc(Builder builder) {
		ApiDocHelper.commonErrorResponse(
			builder
				.summary(SUMMARY_GET)
				.operationId(OPERATION_ID_GET)
				.tag(TAG_CREDIT_SUMMARY)
				.response(responseBuilder()
					.responseCode(String.valueOf(HttpStatus.CREATED.value()))
					.content(contentBuilder()
						.mediaType(MediaType.APPLICATION_JSON_VALUE)
						.schema(schemaBuilder()
							.implementation(ApiResponse.class)
						)
					)
				)
		);
	}
	
}
