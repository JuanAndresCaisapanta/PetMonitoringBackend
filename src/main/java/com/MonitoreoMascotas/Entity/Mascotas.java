package com.MonitoreoMascotas.Entity;

import java.util.Date;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mascotas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String color;
	private String raza;
	private Boolean esterilizacion;
	private Date fechaEsterilizacion;
	private Date fechaNacimiento;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mascotas")
	private List<Profesionales> profesionales;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="especies_id")
	@JsonIgnore
	private Especies especies;
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mascotas")
	private List<Vacunas> vacunas;
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "mascotas")
	private List<Dispositivos> dispositivos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuarios_id")
	private Usuarios usuarios;

	public Mascotas() {
	}


	public Mascotas(String nombre, String color, String raza, Boolean esterilizacion, Date fechaEsterilizacion,
			Date fechaNacimiento, List<Profesionales> profesionales, Especies especies, List<Vacunas> vacunas,
			List<Dispositivos> dispositivos) {
		this.nombre = nombre;
		this.color = color;
		this.raza = raza;
		this.esterilizacion = esterilizacion;
		this.fechaEsterilizacion = fechaEsterilizacion;
		this.fechaNacimiento = fechaNacimiento;
		this.profesionales = profesionales;
		this.especies = especies;
		this.vacunas = vacunas;
		this.dispositivos = dispositivos;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public String getRaza() {
		return raza;
	}


	public void setRaza(String raza) {
		this.raza = raza;
	}


	public Boolean getEsterilizacion() {
		return esterilizacion;
	}


	public void setEsterilizacion(Boolean esterilizacion) {
		this.esterilizacion = esterilizacion;
	}


	public Date getFechaEsterilizacion() {
		return fechaEsterilizacion;
	}


	public void setFechaEsterilizacion(Date fechaEsterilizacion) {
		this.fechaEsterilizacion = fechaEsterilizacion;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public List<Profesionales> getProfesionales() {
		return profesionales;
	}


	public void setProfesionales(List<Profesionales> profesionales) {
		this.profesionales = profesionales;
	}


	public Especies getEspecies() {
		return especies;
	}


	public void setEspecies(Especies especies) {
		this.especies = especies;
	}


	public List<Vacunas> getVacunas() {
		return vacunas;
	}


	public void setVacunas(List<Vacunas> vacunas) {
		this.vacunas = vacunas;
	}


	public List<Dispositivos> getDispositivos() {
		return dispositivos;
	}


	public void setDispositivos(List<Dispositivos> dispositivos) {
		this.dispositivos = dispositivos;
	}


	
}
