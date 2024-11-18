package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class JobNotificationListener implements JobExecutionListener {

        @Override
        public void beforeJob(JobExecution jobExecution) {
            log.info("Job started: {}", jobExecution.getJobInstance().getJobName());

        }

        @Override
        public void afterJob(JobExecution jobExecution) {
            if (jobExecution.getStatus().isUnsuccessful()) {
                log.error("Job failed: {}", jobExecution.getJobInstance().getJobName());
            } else {
                log.info("Job completed successfully: {}", jobExecution.getJobInstance().getJobName());
            }
        }
}
