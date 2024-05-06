package com.springbatch.proyectoStock.process;

import java.util.List;

import com.springbatch.proyectoStock.model.Stock;

public interface StockProcessor {
	List<Stock> process(List<Stock> stock);
}
