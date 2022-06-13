package com.quasar.service;

import javax.xml.bind.ValidationException;

import com.quasar.dto.SateliteDto;
import com.quasar.dto.SateliteSplitDto;
/**
 * Interface con los metodos para descifrar el mensaje
 * @author Favian Ramos
 *
 */
public interface MensageService {
	/**
	 * Metodo para descifrar el mensaje de listado de satelistes
	 * @param sateliteDto Listado de mensajes enviados en la posición
	 * @return El mensaje Descifrado en un String
	 * @throws ValidationException Lanza un exception de tipo validación para cuando no se puede descifrar el mensaje
	 */
	public String descifrarMensaje(SateliteDto sateliteDto) throws ValidationException;
	
	/**
	 * Metodo para descifrar el mensaje para una peticion split de un solo satelite
	 * @param sateliteSplitDto satos del satelite a consultar
	 * @return El mensaje Descifrado en un String
	 * @throws ValidationException Lanza un exception de tipo validación para cuando no se puede descifrar el mensaje
	 */
	public String descifrarMensaje(SateliteSplitDto sateliteSplitDto) throws ValidationException;
}
