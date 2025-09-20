package com.crediya.dailyreportscheduler.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class SchedulerConfig {
	
	private final SchedulerProperties properties;
	
	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(properties.poolSize());
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		scheduler.setAwaitTerminationSeconds(properties.waitTerminateSeconds());
		scheduler.setThreadNamePrefix("daily-report-scheduler-");
		scheduler.initialize();
		return scheduler;
	}
}
