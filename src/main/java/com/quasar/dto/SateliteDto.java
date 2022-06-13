package com.quasar.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase con el listado de satelites
 * 
 * @author Favian Ramos
 *
 */
@Getter
@Setter
@ToString
public class SateliteDto {

	private List<Satelites> satelite;

	/**
	 * Objeto Satelites que contiene los datos del satelite
	 * 
	 * @author Favian Ramos
	 * 
	 */
	@Getter
	@Setter
	@ToString
	public static class Satelites {
		private String name;
		private double distance;
		private List<String> message;
	}

}
