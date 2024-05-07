package com.springbatch.proyectoStock.process;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.proyectoStock.model.Stock;

public class StockProcessor implements ItemProcessor<Stock, Stock> {

	@Override
	public Stock process(Stock item) throws Exception {

		if (item.getLugar().equalsIgnoreCase("PENINSULA")) {
			return item;
		}

		return null;
	}

}
