package com.crediya.usecase.sendreportcreditsummary;

import com.crediya.model.creditsummary.CreditSummary;
import com.crediya.model.creditsummary.gateways.CreditSummaryRepository;
import com.crediya.model.creditsummary.gateways.MessageReportCreditSummaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SendReportCreditSummaryUseCaseTest {

    @InjectMocks
    private SendReportCreditSummaryUseCase useCase;

    @Mock
    private CreditSummaryRepository creditSummaryRepository;

    @Mock
    private MessageReportCreditSummaryService messageReportCreditSummaryService;

    @Test
    void execute_sendsReportSuccessfully() {
        CreditSummary summary = CreditSummary.builder()
                .id("1")
                .totalApprovedCredits(5L)
                .totalAmountApproved(BigDecimal.valueOf(5000))
                .build();

        when(creditSummaryRepository.find()).thenReturn(Mono.just(summary));
        when(messageReportCreditSummaryService.sendReportCreditSummary(summary)).thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute())
                .verifyComplete();

        verify(creditSummaryRepository, times(1)).find();
        verify(messageReportCreditSummaryService, times(1)).sendReportCreditSummary(summary);
    }

    @Test
    void execute_noSummaryFound_doesNotSendReport() {
        when(creditSummaryRepository.find()).thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute())
                .verifyComplete();

        verify(creditSummaryRepository, times(1)).find();
        verifyNoInteractions(messageReportCreditSummaryService);
    }
}

