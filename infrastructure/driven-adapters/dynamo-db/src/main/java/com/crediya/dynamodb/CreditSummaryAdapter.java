package com.crediya.dynamodb;

import com.crediya.dynamodb.mapper.CreditSummaryMapper;
import com.crediya.model.creditsummary.CreditSummary;
import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Slf4j
@Repository
public class CreditSummaryAdapter implements CreditSummaryRepository {
	
	private final DynamoDbAsyncTable<CreditSummaryData> creditSummaryTable;
	
	public CreditSummaryAdapter(
			DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient,
			@Value("${aws.dynamodb.table.credit-summary}") String creditSummaryTableName
		) {
		this.creditSummaryTable = dynamoDbEnhancedAsyncClient.table(creditSummaryTableName, TableSchema.fromBean(CreditSummaryData.class));
	}
	
	@Override
	public Mono<CreditSummary> save(CreditSummary creditSummary) {
		return Mono.fromCallable(() -> CreditSummaryMapper.INSTANCE.toData(creditSummary))
				.doOnSubscribe(subs -> log.trace("Saving CreditSummary: {}", creditSummary))
				.flatMap(data -> Mono.fromFuture(creditSummaryTable.putItem(data))
						.thenReturn(data))
				.map(CreditSummaryMapper.INSTANCE::toDomain)
				.doOnSuccess(saved -> log.info("CreditSummary saved with id: {}", saved.getId()));
	}
	
	@Override
	public Mono<CreditSummary> find() {
		return Mono.from(creditSummaryTable.scan(r -> r.limit(1)))
			.flatMap(page -> Mono.justOrEmpty(page.items().stream().findFirst()))
			.map(CreditSummaryMapper.INSTANCE::toDomain)
			.doOnSubscribe(subs -> log.trace("Finding CreditSummary"))
			.doOnSuccess(found -> {
				if (found != null) log.info("CreditSummary found with id: {}", found.getId());
			});
	}
}
