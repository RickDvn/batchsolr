package com.viewnext.batchsolr.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Listener de los Jobs para que cuando acaben se notifique su estado
 */
@Configuration
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	public JobCompletionNotificationListener() {
	}

	/**
	 * Realiza un log cuando se completa un job
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		switch(jobExecution.getStatus()) {
		case COMPLETED:
			log.info("!!! JOB FINISHED! Time to verify the results");
			break;
		case FAILED:
			log.info("!!! JOB Failed");
			break;
		case ABANDONED:
			log.info("!!! JOB Abandoned");
			break;
		default:
			break;
		}
	}
}
