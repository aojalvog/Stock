package com.springbatch.proyectoStock.process;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.proyectoStock.model.Stock;

public class StockProcessor implements ItemProcessor<Stock, Stock> {

	/**
	 * Procesa un objeto de tipo Stock. Si el lugar del stock es "PENINSULA",
	 * devuelve el objeto sin modificar. De lo contrario, devuelve null para indicar
	 * que el objeto debe ser filtrado.
	 *
	 * @param item el objeto Stock a procesar
	 * @return el objeto Stock sin modificar si el lugar es "PENINSULA", o null si
	 *         el lugar no es "PENINSULA"
	 * @throws Exception si ocurre un error durante el procesamiento
	 */

	@Override
	public Stock process(Stock item) throws Exception {

		if (item.getLugar().equalsIgnoreCase("PENINSULA")) {
			return item;
		}

		return null;
	}

}
