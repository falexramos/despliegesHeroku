package com.quasar.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto que se devuelve con el mensaje de error a la petición cuando esta
 * falla.
 * 
 * @author Favian Ramos
 *
 */
@Setter
@Getter

public class ErrorMessageDTO implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public ErrorMessageDTO(String message) {
		super();
		this.message = message;
	}

	private String message;
}
