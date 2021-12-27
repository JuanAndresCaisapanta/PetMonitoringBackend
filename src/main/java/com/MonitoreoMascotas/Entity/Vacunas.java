package com.MonitoreoMascotas.Entity;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vacunas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fabricador;
	private String lote;
	private String aplicador;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaFabricacion;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaCaducidad;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaAplicacion;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaRevacuna;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="mascotas_id")
	@JsonIgnoreProperties({"profesionales","vacunas","dispositivos"})
	private Mascotas mascotas;

}
