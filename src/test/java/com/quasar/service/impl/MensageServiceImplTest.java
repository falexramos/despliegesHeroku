package com.quasar.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.quasar.dto.SateliteDto;
import com.quasar.dto.SateliteDto.Satelites;

@SpringBootTest
public class MensageServiceImplTest {

	private static Logger logger = LoggerFactory.getLogger(MensageServiceImplTest.class);
	
	@Autowired
	private MensageServiceImpl mensageServiceImpl;

	@Test
	public void descifrarMensajeCuandoLaSolicitudEsDeTresSatelitesEntoncesDescifrar() {
		List<List<String>> mensaje = new ArrayList<List<String>>();

		String[] mensajeSateliteUno = { "este", "", "", "mensaje", "" };
		String[] mensajeSatelitedos = { "", "es", "", "", "secreto" };
		String[] mensajeSateliteTres = { "este", "", "un", "", "" };
		mensaje.add(Arrays.stream(mensajeSateliteUno).collect(Collectors.toList()));
		mensaje.add(Arrays.stream(mensajeSatelitedos).collect(Collectors.toList()));
		mensaje.add(Arrays.stream(mensajeSateliteTres).collect(Collectors.toList()));

		List<Satelites> sateliteDto = (List<Satelites>) new SateliteDto();
		Satelites satelites = new Satelites();
		satelites.setName("kenobi");
		satelites.setDistance(100);
		satelites.setMessage(mensaje.get(0));		
		sateliteDto.add(satelites);
		
		satelites.setName("skywalker");
		satelites.setDistance(115.5);
		satelites.setMessage(mensaje.get(1));	
		sateliteDto.add(satelites);
		
		satelites.setName("sato");
		satelites.setDistance(142.7);
		satelites.setMessage(mensaje.get(2));	
		sateliteDto.add(satelites);
		
		SateliteDto sateliteDtoPrueba= new SateliteDto();
		sateliteDtoPrueba.setSatelite(sateliteDto);
		
		String mensajeRecibido = mensageServiceImpl.descifrarMensaje(sateliteDtoPrueba);
		
		String MesajeEsperado = "este es un mensaje secreto";
		assertEquals(mensajeRecibido, MesajeEsperado);
	}
}
