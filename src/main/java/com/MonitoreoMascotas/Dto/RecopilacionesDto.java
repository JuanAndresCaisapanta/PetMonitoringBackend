package com.MonitoreoMascotas.Dto;

import javax.validation.constraints.NotNull;

import com.MonitoreoMascotas.Entity.Dispositivos;

public class RecopilacionesDto {
	
	private Float latitud;
	private Float longitud;
	private Float temperatura;
	@NotNull
	private Dispositivos dispositivos;
	
	public RecopilacionesDto() {
	}

	public RecopilacionesDto(Float latitud, Float longitud, Float temperatura, @NotNull Dispositivos dispositivos) {
		this.latitud = latitud;
		this.longitud = longitud;
		this.temperatura = temperatura;
		this.dispositivos = dispositivos;
	}

	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public Float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}

	public Dispositivos getDispositivos() {
		return dispositivos;
	}

	public void setDispositivos(Dispositivos dispositivos) {
		this.dispositivos = dispositivos;
	}
	
	
}
