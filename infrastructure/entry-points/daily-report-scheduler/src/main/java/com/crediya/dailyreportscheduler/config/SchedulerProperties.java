package com.crediya.dailyreportscheduler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "entrypoint.scheduler")
public record SchedulerProperties(
	String cron,
	int poolSize,
	int waitTerminateSeconds
) {}
