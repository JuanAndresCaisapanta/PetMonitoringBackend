package com.MonitoreoMascotas.Dto;

import com.MonitoreoMascotas.Entity.Mascotas;
import com.MonitoreoMascotas.Security.Entity.Usuarios;

public class DispositivosDto {
	
	private String nombre;
	private String marca;
	private String fabricante;
	private String observacion;
	private Mascotas mascotas;
	private Usuarios usuarios;
	
	
	public DispositivosDto() {
	}

	public DispositivosDto(String nombre, String marca, String fabricante, String observacion, Mascotas mascotas, Usuarios usuarios) {
		this.nombre = nombre;
		this.marca = marca;
		this.fabricante = fabricante;
		this.observacion = observacion;
		this.mascotas = mascotas;
		this.usuarios = usuarios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Mascotas getMascotas() {
		return mascotas;
	}

	public void setMascotas(Mascotas mascotas) {
		this.mascotas = mascotas;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}
