package com.quasar.service;

import com.quasar.dto.RespuestaDto;
import com.quasar.dto.SateliteDto;
import com.quasar.dto.SateliteDto.Satelites;
import com.quasar.dto.SateliteSplitDto;
/**
 * Interface de implementación para obtener la posicion y descifrar el mensaje 
 * @author Favian Ramos
 *
 */
public interface LocationService {
	/**
	 * Método para obtener la locacion y descifrar el mensaje
	 * @param satelite listado con los datos de los satelistes a validar
	 * @return la posición X y Y y el mensaje descifrado
	 */
		public abstract RespuestaDto getLocation(SateliteDto satelite);
		
/**
 * Método para obtener la locacion y descifrar el mensaje cuando la solicitud es Split de un solo satelite
 * @param sateliteSplitDto el satelite con la distancia y el mensaje a descifrar
 * @return la posición X y Y y el mensaje descifrado
 */
		public abstract RespuestaDto getLocationSplit(SateliteSplitDto sateliteSplitDto);

		
		
	
}
