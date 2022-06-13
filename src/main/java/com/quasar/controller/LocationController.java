package com.quasar.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quasar.dto.RespuestaDto;
import com.quasar.dto.SateliteDto;
import com.quasar.service.LocationService;
/**
 * Controlador de las Apis de la operacion quasar.
 * @author Favian Ramos.
 *
 */
@RestController
@RequestMapping("/topsecret")
public class LocationController {
	
	private static Logger logger=LoggerFactory.getLogger(LocationController.class);
	
	@Autowired
	private LocationService locationService;
	
	/**
	 * Api rest de tipo Post /topsecret para validar la posición y descifrar el mensaje.
	 * @param sateliteDto objeto de tipo sateliteDto, un array de satelites con la distancia a obtener y el mensaje a descifrar.
	 * @return Retorna un Objeto con la Posicion X y Y de los satelites y el mensaje descifrado.
	 */
	@CrossOrigin(origins =  "*")
	@PostMapping
	public RespuestaDto getLocation(@RequestBody SateliteDto sateliteDto) {		
		
		return locationService.getLocation(sateliteDto);		
		
	}
	
	
	
}
