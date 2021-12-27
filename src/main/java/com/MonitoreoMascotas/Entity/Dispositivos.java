package com.MonitoreoMascotas.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.MonitoreoMascotas.Security.Entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String marca;
	private String fabricante;
	private String observacion;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "dispositivos")
	private List<Recopilaciones> recopilaciones;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="mascotas_id")
	@JsonIgnoreProperties({"dispositivos","profesionales","vacunas","recopilaciones","usuarios"})
	private Mascotas mascotas;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuarios_id")
	@JsonIgnoreProperties({"dispositivos","usuarios","mascotas"})
	private Usuarios usuarios;
	
}
