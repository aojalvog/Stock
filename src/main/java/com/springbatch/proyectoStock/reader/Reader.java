package com.springbatch.proyectoStock.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.springbatch.proyectoStock.model.Stock;

@Component
// Importa el LOGGER

public class Reader {

	@Bean
	FlatFileItemReader<Stock> stockReader() {

		// Creo un lector de texto plano que me devuelve objetos STOCK
		FlatFileItemReader<Stock> reader = new FlatFileItemReader<>();
		// Selecciona la ruta del archivo que va a leer
		reader.setResource(new ClassPathResource("stockTerminales.dat"));
		// Skipeo una línea porque la primera línea del documento es la cabezera.
		reader.setLinesToSkip(1);
		// Le paso cómo quiero que me lo lea
		reader.setLineMapper(lineMapper());
		return reader;
	}

	// Método que dice cómo quiero leer
	private LineMapper<Stock> lineMapper() {
		DefaultLineMapper<Stock> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		// Te dice lo que separa los diferentes puntos
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("lugar", "id", "stockTotal", "stockReal", "stockVirtual");

		BeanWrapperFieldSetMapper<Stock> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		// Esto es a lo que vamos a convertir
		fieldSetMapper.setTargetType(Stock.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}

}
