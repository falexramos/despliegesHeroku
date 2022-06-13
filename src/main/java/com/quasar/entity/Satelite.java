package com.quasar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="satelite")
@Setter
@Getter
@ToString

/**
 * Objeto que contine los datos de las posiciones de los satelistes X y Y
 * @author Favian Ramos
 *
 */
public class Satelite {
	@Id
	@Column(name="nombre")
	private String nombreSatelite;
	
	@Column(name="X")
	private double positionX;
	
	@Column(name="Y")
	private double positionY;

	
}
