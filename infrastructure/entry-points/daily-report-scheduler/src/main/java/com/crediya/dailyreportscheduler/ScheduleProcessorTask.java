package com.crediya.dailyreportscheduler;

import com.crediya.model.creditsummary.ports.ISendReportCreditSummaryUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleProcessorTask {
	
	private final ISendReportCreditSummaryUseCase sendReportCreditSummaryUseCase;
	
	@Scheduled(cron = "${entrypoint.scheduler.jobs.cron-daily-report}")
	public void executeDailyReportTask() {
		log.info("Start daily report task");
		sendReportCreditSummaryUseCase.execute()
			.doOnError(error -> log.error("Error executing daily report task", error))
			.subscribe();
	}
}
