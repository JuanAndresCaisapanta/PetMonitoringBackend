package com.Pet_Monitoring.Dto;



import com.Pet_Monitoring.Entity.Device;
import com.Pet_Monitoring.Entity.Pet;

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
	private String fecha;
	private String hora;
	private Device dispositivos;
	private Pet mascotas;

}
