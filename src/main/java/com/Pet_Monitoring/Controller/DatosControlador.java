package com.Pet_Monitoring.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Pet_Monitoring.Dto.Mensaje;
import com.Pet_Monitoring.Dto.RecopilacionesDto;
import com.Pet_Monitoring.Entity.Device_Data;
import com.Pet_Monitoring.Service.RecopilacionesService;

@RestController
@RequestMapping("/recopilaciones")
@CrossOrigin(origins = "http://localhost:3000")
public class DatosControlador {
	
	@Autowired
	RecopilacionesService recopilacionesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Device_Data>> lista() {
		List<Device_Data> list = recopilacionesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/*
	 * @PostMapping(produces = "application/json") public ResponseEntity<?>
	 * crear(@RequestBody @Validated RecopilacionesDto recopilacionesDto) {
	 * DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm");
	 * DateTimeFormatter fecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	 * Device_Data recopilacion= new Device_Data();
	 * recopilacion.setLatitud(recopilacionesDto.getLatitud());
	 * recopilacion.setLongitud(recopilacionesDto.getLongitud());
	 * recopilacion.setTemperatura(recopilacionesDto.getTemperatura());
	 * recopilacion.setHora(hora.format(LocalDateTime.now()));
	 * recopilacion.setFecha(fecha.format(LocalDateTime.now()));
	 * recopilacion.setDispositivos(recopilacionesDto.getDispositivos());
	 * recopilacion.setMascotas(recopilacionesDto.getMascotas());
	 * recopilacionesService.guardar(recopilacion); return new ResponseEntity<>(new
	 * Mensaje("Recopilacion creada"), HttpStatus.OK); }
	 */

}
