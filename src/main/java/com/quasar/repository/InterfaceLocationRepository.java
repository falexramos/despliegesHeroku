package com.quasar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quasar.entity.Satelite;

/**
 * Interface de los metodos del repository de consulta a la base de datos
 * @author Favian Ramos
 *
 */
@Repository
public interface InterfaceLocationRepository extends JpaRepository<Satelite, Integer> {
	/**
	 * Consulta por nombre del satelite
	 * @param sateliteName nombre del satelite a consultar
	 * @return los datos de la posicion del satelite 
	 */
	@Query("Select c from Satelite c where c.nombreSatelite = ?1")
	List<Satelite> findByName(String sateliteName);

}
