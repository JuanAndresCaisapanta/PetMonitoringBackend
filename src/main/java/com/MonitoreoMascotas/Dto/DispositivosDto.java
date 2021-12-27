package com.MonitoreoMascotas.Dto;




import com.MonitoreoMascotas.Entity.Mascotas;
import com.MonitoreoMascotas.Security.Entity.Usuarios;

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
	private Mascotas mascotas;
	private Usuarios usuarios;

}
