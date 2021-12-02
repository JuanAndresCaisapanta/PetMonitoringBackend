package com.MonitoreoMascotas.Dto;

import com.MonitoreoMascotas.Entity.Mascotas;

public class DispositivosDto {
	
	private String nombre;
	private String marca;
	private String fabricante;
	private String observacion;
	private Mascotas mascotas;
	
	public DispositivosDto() {
	}

	public DispositivosDto(String nombre, String marca, String fabricante, String observacion, Mascotas mascotas) {
		this.nombre = nombre;
		this.marca = marca;
		this.fabricante = fabricante;
		this.observacion = observacion;
		this.mascotas = mascotas;
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

}
