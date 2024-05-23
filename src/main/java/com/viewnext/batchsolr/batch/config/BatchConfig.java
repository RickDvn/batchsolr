package com.viewnext.batchsolr.batch.config;

import org.apache.solr.common.SolrInputDocument;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.viewnext.batchsolr.model.Personaje;
import com.viewnext.batchsolr.batch.listener.JobCompletionNotificationListener;
import com.viewnext.batchsolr.batch.processor.PersonajeItemProcessor;
import com.viewnext.batchsolr.batch.writer.PersonajeItemWriter;

/**
 * La configuracion del batch con sus steps
 */
@Configuration
public class BatchConfig {
	
	@Bean
	public Job importUserJob (JobRepository jobRepository, Step step1, JobCompletionNotificationListener listener) {
		return new JobBuilder("importUserJob", jobRepository)
				.listener(listener)
				.start(step1)
				.build();
	}
	
	/**
	 * Escribir en un csv en local leyendo de la base de datos
	 * 
	 * @param jobRepository
	 * @param transactionManager
	 * @param reader
	 * @param processor
	 * @param writer
	 * @return
	 */
	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager, @Qualifier("readerDB") JdbcCursorItemReader<Personaje> reader,
			PersonajeItemProcessor processor, @Qualifier("writerSolr") PersonajeItemWriter writer) {
		return new StepBuilder("step1", jobRepository)
				.<Personaje, SolrInputDocument> chunk(3, transactionManager)
				.allowStartIfComplete(true)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}
}
