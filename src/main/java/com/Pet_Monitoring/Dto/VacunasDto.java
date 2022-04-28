package com.Pet_Monitoring.Dto;





import java.util.Date;

import com.Pet_Monitoring.Entity.Pet;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacunasDto {
	
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
	private Pet mascotas;
	
}
