package com.MonitoreoMascotas.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesionales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;

	@ManyToOne(optional = false)
	@JoinColumn(name = "mascotas_id")
	@JsonIgnoreProperties("profesionales")
	private Mascotas mascotas;

	@ManyToOne(optional = false)
	@JoinColumn(name = "especialidades_id")
	@JsonIgnoreProperties("profesionales")
	private Especialidades especialidades;

}
