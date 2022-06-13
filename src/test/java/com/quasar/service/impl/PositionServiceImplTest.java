package com.quasar.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PositionServiceImplTest {

	@Autowired
	private PositionServiceImpl positionService;

	@Test
	public void obetnerLocacionCuandoSateliteCompletoEntoncesObtienePosicion() {
		double[][] posiciones = new double[][] {{-500.0, -200.0}, {100.0, -100.0}, {500.0, 100.0}};
		double[] distancias = new double[] {100.0, 115.5, 142.7};
		double[] posicionEsperada = new double[] { -58.315252587138595, -69.55141837312165};
		double[] posicionDevuelta = positionService.obtenerLocalicacion(posiciones, distancias);

		for (int i = 0; i < posicionDevuelta.length; i++) {
			assertEquals(posicionEsperada[i], posicionDevuelta[i]);
		}

	}

}
