package com.crediya.sqs.listener;

import com.crediya.model.creditsummary.ports.IIncrementCreditSummaryUseCase;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.sqs.model.Message;

import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class SQSCreditApprovedProcessor implements Function<Message, Mono<Void>> {
    
    private final IIncrementCreditSummaryUseCase incrementCreditSummaryUseCase;
    private final Gson gson = new Gson();

    @Override
    public Mono<Void> apply(Message message) {
        return Mono.fromCallable(() -> gson.fromJson(message.body(), CreditApprovedMessage.class))
            .flatMap(bodyMessage -> incrementCreditSummaryUseCase.execute(bodyMessage.amount()))
            .then();
    }
}
