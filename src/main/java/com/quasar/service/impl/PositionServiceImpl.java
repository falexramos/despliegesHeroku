package com.quasar.service.impl;

import java.util.List;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.quasar.controller.LocationController;
import com.quasar.dto.SateliteDto;
import com.quasar.dto.SateliteDto.Satelites;
import com.quasar.dto.SateliteSplitDto;
import com.quasar.entity.Satelite;
import com.quasar.util.Position;

/**
 * Implementación con los Métodos para obtener la localicación de la petición.
 * 
 * @author Favian Ramos
 *
 */
@Service
public class PositionServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(LocationController.class);

	/**
	 * Método para determinar la posición de los satelistes enviados en la petición.
	 * 
	 * @param satelites   listado de satelites y sus pocisiones obtenidos de la
	 *                    consulta a la base de datos.
	 * @param sateliteDto objeto con los satelistes enviados en la petición.
	 * @return Objeto de tipo Posicion con la ubicacion en X y Y.
	 */
	public Position satelitePosicion(List<Satelite> satelites, SateliteDto sateliteDto) {

		double[] extraerDistancias = obtenerDistancias(sateliteDto);
		double[][] extraerPosiciones = obtenerPosiciones(satelites);

		double[] posicionRespuesta = obtenerLocalicacion(extraerPosiciones, extraerDistancias);
		Position resPosition = new Position();
		resPosition.setX(posicionRespuesta[0]);
		resPosition.setY(posicionRespuesta[1]);
		return resPosition;

	}

	/**
	 * Método split para determinar la posición de los satelistes enviados en la
	 * petición split.
	 * 
	 * @param satelite         listado de satelites y sus pocisiones obtenidos de la
	 *                         consulta a la base de datos.
	 * @param sateliteSplitDto objeto con los datos del satelite enviado en la
	 *                         petición.
	 * @return Objeto de tipo Posicion con la ubicacion en X y Y.
	 */
	public Position satelitePosicion(List<Satelite> satelite, SateliteSplitDto sateliteSplitDto) {
		double[] extraerDistancias = new double[1];
		extraerDistancias[0] = sateliteSplitDto.getDistance();
		double[][] extraerPosiciones = obtenerPosiciones(satelite);

		double[] posicionRespuesta = obtenerLocalicacion(extraerPosiciones, extraerDistancias);
		Position resPosition = new Position();
		resPosition.setX(posicionRespuesta[0]);
		resPosition.setY(posicionRespuesta[1]);
		return resPosition;
	}

	/**
	 * Método Para obtener las posiciones de los satelistes alamcenados.
	 * 
	 * @param satelites listado de pocisiones de los satelistes almacenados.
	 * @return arreglo de dos dimensiones de tipo double con los datos de las
	 *         posiciones X y Y.
	 */

	private double[][] obtenerPosiciones(List<Satelite> satelites) {
		double[][] posiciones = new double[satelites.size()][2];

		for (int x = 0; x < posiciones.length; x++) {
			posiciones[x][0] = satelites.get(x).getPositionX();
			posiciones[x][1] = satelites.get(x).getPositionY();
		}

		return posiciones;
	}

	/**
	 * Método Para obtener las distancias enviadas en la petición.
	 * 
	 * @param sateliteDto objeto enviado en la petición.
	 * @return arreglo de tipo double con los datos de las distancias enviadas en la
	 *         petición.
	 */
	public double[] obtenerDistancias(SateliteDto sateliteDto) {
		double[] distancias = new double[sateliteDto.getSatelite().size()];
		int i = 0;
		for (Satelites s : sateliteDto.getSatelite()) {
			distancias[i] = s.getDistance();
			i++;
		}
		return distancias;
	}

	/**
	 * Método para obtener la localización en X y Y, utilizando la
	 * TrilaterationFunction.
	 * 
	 * @param posiciones arreglo de dos dimensiones de tipo double con los datos de
	 *                   las posiciones X y Y.
	 * @param distancias arreglo de una dimensión de tipo double con los datos de
	 *                   las distancias enviadas en la petición.
	 * @return arreglo de tipo double con los datos de la posicion en X y Y.
	 */
	public double[] obtenerLocalicacion(double[][] posiciones, double[] distancias) {

		TrilaterationFunction trilaterationFunction = new TrilaterationFunction(posiciones, distancias);
		NonLinearLeastSquaresSolver nonLinearLeastSquaresSolver = new NonLinearLeastSquaresSolver(trilaterationFunction,
				new LevenbergMarquardtOptimizer());
		return nonLinearLeastSquaresSolver.solve().getPoint().toArray();
	}

}
