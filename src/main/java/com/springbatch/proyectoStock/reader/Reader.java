package com.springbatch.proyectoStock.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.springbatch.proyectoStock.model.Stock;

public class Reader {

	public static final Logger log = LoggerFactory.getLogger(Reader.class);

	@Value("${app.file.input}")
	private String stockFile;

	// Lectura del dat de stockTerminales.dat
	@Bean
	public FlatFileItemReader<Stock> reader() {
		log.info("Leyendo fichero...");
		return new FlatFileItemReaderBuilder<Stock>().name("stockItemReader").resource(new ClassPathResource(stockFile))
				.delimited().names("lugar", "id", "stock", "stockReal", "stockVirtual")
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Stock>() {
					{
						setTargetType(Stock.class);
					}
				}).linesToSkip(1) // salta la primera línea, que es la cabecera
				.build();
	}
}

/*
 * @Override public List<Stock> read() { List<Stock> stockFiltrado = new
 * ArrayList<>(); log.info("Leyendo fichero"); for (Stock stock : stockFiltrado)
 * { if (stock.getLugar().equals("PENINSULA")) { stockFiltrado.add(stock); } }
 * 
 * return stockFiltrado; }
 */
