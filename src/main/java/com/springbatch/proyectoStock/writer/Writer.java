package com.springbatch.proyectoStock.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Component;

import com.springbatch.proyectoStock.model.Stock;

@Component
public class Writer {

	@Bean
	FlatFileItemWriter<Stock> itemWriter() throws Exception {
		BeanWrapperFieldExtractor<Stock> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] { "lugar", "id", "stockTotal", "stockReal", "stockVirtual" });
		fieldExtractor.afterPropertiesSet();

		DelimitedLineAggregator<Stock> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(",");
		lineAggregator.setFieldExtractor(fieldExtractor);

		return new FlatFileItemWriterBuilder<Stock>().name("outputStock.csv")
				.resource(new PathResource("outputStock.csv")).lineAggregator(lineAggregator).build();
	}
}
