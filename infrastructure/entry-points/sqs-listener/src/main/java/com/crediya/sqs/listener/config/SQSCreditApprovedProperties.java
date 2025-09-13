package com.crediya.sqs.listener.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "entrypoint.sqs.credit-approved")
public record SQSCreditApprovedProperties(
        String region,
        String endpoint,
        String queueUrl,
        int waitTimeSeconds,
        int visibilityTimeoutSeconds,
        int maxNumberOfMessages,
        int numberOfThreads
) {}
