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

	/**
	 * Crea y configura un escritor de archivos planos para escribir objetos de tipo
	 * Stock en un archivo CSV. Este escritor utiliza un extractor de campos de
	 * BeanWrapper para extraer los valores de los campos de los objetos Stock y un
	 * agregador de líneas delimitadas para unir los valores de los campos en líneas
	 * de texto.
	 *
	 * @return un escritor de archivos planos configurado para escribir objetos
	 *         Stock en un archivo CSV.
	 * @throws Exception si ocurre un error durante la configuración del escritor.
	 */

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
