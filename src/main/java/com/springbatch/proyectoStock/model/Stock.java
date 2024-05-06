package com.springbatch.proyectoStock.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Stock {

	private String lugar;

	private Long id;

	private Long stock;

	private Long stockReal;

	private Long stockVirtual;

}
