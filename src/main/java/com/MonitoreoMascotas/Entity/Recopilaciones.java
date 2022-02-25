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
@NoArgsConstructor
@AllArgsConstructor
public class Recopilaciones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Float latitud;
	private Float longitud;
	private Float temperatura;
	private String fecha;
	private String hora;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="dispositivos_id")
	@JsonIgnoreProperties({"recopilaciones", "mascotas","usuarios"})
	private Dispositivos dispositivos;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="mascotas_id")
	@JsonIgnoreProperties({"recopilaciones","profesionales","vacunas","usuarios","dispositivos"})
	private Mascotas mascotas;

}
