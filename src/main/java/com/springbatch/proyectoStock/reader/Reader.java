package com.springbatch.proyectoStock.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.springbatch.proyectoStock.model.Stock;

import lombok.extern.slf4j.Slf4j;

@Component
// Importa el LOGGER
@Slf4j
public class Reader {

	public FlatFileItemReader<Stock> reader() {

		FlatFileItemReader<Stock> reader = new FlatFileItemReader<>();
		// Selecciona la ruta del archivo que va a leer
		reader.setResource(new ClassPathResource("stockTerminales.dat"));
		// Skipeo una línea porque la primera línea del documento es la cabezera.
		reader.setLinesToSkip(1);
		reader.setLineMapper(lineMapper());
		return reader;
	}

	private LineMapper<Stock> lineMapper() {
		DefaultLineMapper<Stock> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		// Te dice lo que separa los diferentes puntos
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setNames("lugar", "id", "stockTotal", "stockReal", "stockVirtual");

		BeanWrapperFieldSetMapper<Stock> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		// Esto es a lo que vamos a convertir
		fieldSetMapper.setTargetType(Stock.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		return lineMapper;
	}

}
