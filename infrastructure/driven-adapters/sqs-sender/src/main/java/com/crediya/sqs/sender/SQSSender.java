package com.crediya.sqs.sender;

import com.crediya.model.creditsummary.CreditSummary;
import com.crediya.model.creditsummary.gateways.MessageReportCreditSummaryService;
import com.crediya.sqs.sender.config.SQSSenderProperties;
import com.crediya.sqs.sender.dto.output.MessageReportCreditApproved;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@Slf4j
@Service
public class SQSSender implements MessageReportCreditSummaryService {
    
    private final SQSSenderProperties properties;
    private final SqsAsyncClient client;
    
    private final Gson gson = new Gson();
    
    public SQSSender(
        SQSSenderProperties properties,
        @Qualifier("sqsSenderClient")
        SqsAsyncClient client
    ) {
        this.properties = properties;
        this.client = client;
    }
    
    @Override
    public Mono<String> sendReportCreditSummary(CreditSummary creditSummary) {
        return Mono.fromCallable(() -> new MessageReportCreditApproved(
                creditSummary.getTotalApprovedCredits(),
                creditSummary.getTotalAmountApproved()
            ))
            .map(gson::toJson)
            .flatMap(this::send);
    }
    
    private Mono<String> send(String message) {
        return Mono.fromCallable(() -> buildRequest(message))
                .flatMap(request -> Mono.fromFuture(client.sendMessage(request)))
                .doOnNext(response -> log.debug("Message sent {}", response.messageId()))
                .map(SendMessageResponse::messageId);
    }

    private SendMessageRequest buildRequest(String message) {
        return SendMessageRequest.builder()
                .queueUrl(properties.queueUrl())
                .messageBody(message)
                .build();
    }
}
