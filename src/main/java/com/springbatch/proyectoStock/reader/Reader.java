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

public class Reader {

	/**
	 * Crea y configura un lector de archivos planos para leer objetos de tipo Stock
	 * desde un archivo. Este lector utiliza un mapeador de líneas para convertir
	 * líneas de texto en objetos Stock.
	 *
	 * @return un lector de archivos planos configurado para leer objetos Stock
	 *         desde un archivo.
	 */

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

	/**
	 * Configura y devuelve un mapeador de líneas para convertir líneas de texto en
	 * objetos de tipo Stock. Este mapeador utiliza un tokenizador delimitado para
	 * dividir las líneas de texto en campos y un mapeador de conjuntos de campos de
	 * BeanWrapper para convertir los campos en objetos Stock.
	 *
	 * @return un mapeador de líneas configurado para convertir líneas de texto en
	 *         objetos Stock.
	 */

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
