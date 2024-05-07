package com.springbatch.proyectoStock.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.springbatch.proyectoStock.listener.JobCompletionNotificationListener;
import com.springbatch.proyectoStock.model.Stock;
import com.springbatch.proyectoStock.process.StockProcessor;

@Configuration
public class BatchConfig {

//	private Writer writer;
//	private Reader reader;
//	private Processor processor;

	@Bean
	JobCompletionNotificationListener listener() {
		return new JobCompletionNotificationListener();
	}

	@Bean
	Job job(JobRepository jobRepository, Step step, JobCompletionNotificationListener listener) {
		return new JobBuilder("dat-to-csv", jobRepository).listener(listener).start(step).build();
	}

	@Bean
	Step step(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
			FlatFileItemReader<Stock> reader, StockProcessor processor, FlatFileItemWriter<Stock> writer) {
		return new StepBuilder("step1", jobRepository).<Stock, Stock>chunk(10, transactionManager).reader(reader)
				.processor(processor).writer(writer).build();
	}
}
