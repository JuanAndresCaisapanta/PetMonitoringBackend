package com.Pet_Monitoring.Dto;




import com.Pet_Monitoring.Security.Entity.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispositivosDto {
	
	private String nombre;
	private String marca;
	private String fabricante;
	private String observacion;
	private Usuarios usuarios;

}
