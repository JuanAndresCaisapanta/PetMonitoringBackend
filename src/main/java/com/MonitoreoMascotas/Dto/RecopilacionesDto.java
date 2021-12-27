package com.MonitoreoMascotas.Dto;

import javax.validation.constraints.NotNull;

import com.MonitoreoMascotas.Entity.Dispositivos;
import com.MonitoreoMascotas.Entity.Mascotas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecopilacionesDto {

	private Float latitud;
	private Float longitud;
	private Float temperatura;
	@NotNull
	private Dispositivos dispositivos;
	private Mascotas mascotas;

}
