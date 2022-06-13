package com.quasar.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.quasar.controller.LocationController;
import com.quasar.dto.RespuestaDto;
import com.quasar.dto.SateliteDto;
import com.quasar.dto.SateliteDto.Satelites;
import com.quasar.dto.SateliteSplitDto;
import com.quasar.service.MensageService;

/**
 * Implementación del Mensaje Service para descifrar el mensaje
 * 
 * @author Favian Ramos
 *
 */
@Service
public class MensageServiceImpl implements MensageService {

	private static Logger logger = LoggerFactory.getLogger(LocationController.class);
	private RespuestaDto respuestaDto;
	private String TEXTO_FRASE = "";
	private String NUMERO_MENSAJES_INSUFICIENTES = "Numero de Mensajes insufientes";
	private String NO_ES_POSIBLE_CONOCER_MENSAJE = "No es posible conocer el mensaje";
	private String MENSAJE_DESCIFRADO = "";
	private int TAMANO_MINIMO_MENSAJE = 2;

	public String descifrarMensaje(SateliteDto sateliteDto) throws ServiceException {

		return validacionesMensaje(sateliteDto);
	}

	public String descifrarMensaje(SateliteSplitDto sateliteSplitDto) throws ServiceException {
		return validacionesMensaje(sateliteSplitDto);
	}

	/**
	 * Metodo que realiza las validaciones del mensaje
	 * 
	 * @param sateliteDto Recibe el listado de satelites
	 * @return el mensaje descifrado o el error de que no se puedo descifrar
	 */

	public String validacionesMensaje(SateliteDto sateliteDto) {

		List<List<String>> mensages = obtenerarrayMensajes(sateliteDto);

		if (validaTamanoMinimoMensaje(mensages)) {
			throw new ServiceException(NO_ES_POSIBLE_CONOCER_MENSAJE);
		} else {
			MENSAJE_DESCIFRADO = descifrarMensaje(mensages);
		}
		return MENSAJE_DESCIFRADO;

	}

	/**
	 * Metodo que realiza las validaciones del mensaje
	 * 
	 * @param sateliteSplitDto recibe un satelite
	 * @return el mensaje descifrado o el error de que no se puedo descifrar
	 */
	private String validacionesMensaje(SateliteSplitDto sateliteSplitDto) {
		List<List<String>> mensages = obtenerArrayMensajes(sateliteSplitDto);

		if (validaTamanoMinimoMensajeSplit(mensages)) {
			throw new ServiceException(NO_ES_POSIBLE_CONOCER_MENSAJE);
		} else {
			MENSAJE_DESCIFRADO = descifrarMensaje(mensages);
		}
		return MENSAJE_DESCIFRADO;
	}

	/**
	 * Metodo para realizar la validación Split del tamaño minimo de la lista
	 * mensajes recibidos
	 * 
	 * @param mensages Listado de Mensajes a validar
	 * @return falso u verdadero
	 */
	private boolean validaTamanoMinimoMensajeSplit(List<List<String>> mensages) {
		return (mensages.get(0).size() < TAMANO_MINIMO_MENSAJE ? true : false);
	}

	/**
	 * Metodo para realizar la validación del tamaño minimo de la lista mensajes
	 * recibidos
	 * 
	 * @param messages Listado de Mensajes a validar
	 * @return falso u verdadero
	 */
	public boolean validaTamanoMinimoMensaje(List<List<String>> messages) {
		return (messages.size() < TAMANO_MINIMO_MENSAJE ? true : false);

	}

	/**
	 * Método para descifrar el mensaje
	 * 
	 * @param messages listado de mensajes de tipo array sin espacios con datos
	 *                 validos
	 * @return el mensaje descifrado de tipo String
	 */
	public String descifrarMensaje(List<List<String>> messages) {
		TEXTO_FRASE = "";
		for (List<String> mensaje : messages) {
			if (mensaje.size() > 0 && !mensaje.get(0).equals("")) {
				if (mensaje.size() == 1) {
					TEXTO_FRASE = mensaje.get(0);
				} else {
					TEXTO_FRASE = mensaje.get(0) + " ";
				}
				messages.stream().forEach(p -> p.remove(0));
				return TEXTO_FRASE + descifrarMensaje(messages);
			}
		}

		return "";

	}

	/**
	 * Método para obtener el array de los datos validos enviados en la petición
	 * 
	 * @param sateliteDto los satelites con los datos de la petición
	 * @return Listado de tipo Array con los mensajes validos enviados en la
	 *         petición
	 */
	public List<List<String>> obtenerarrayMensajes(SateliteDto sateliteDto) {
		List<List<String>> messages = new ArrayList<List<String>>();
		for (Satelites s : sateliteDto.getSatelite()) {
			messages.add(s.getMessage());
		}

		return messages;
	}

	/**
	 * Método para obtener el array de los datos validos enviados en la petición
	 * Split
	 * 
	 * @param sateliteSplitDto el satelite con los datos de la petición
	 * @return Listado de tipo Array con los mensajes validos enviados en la
	 *         petición
	 */
	private List<List<String>> obtenerArrayMensajes(SateliteSplitDto sateliteSplitDto) {
		List<List<String>> messages = new ArrayList<List<String>>();

		messages.add(sateliteSplitDto.getMessage());

		return messages;
	}

}
