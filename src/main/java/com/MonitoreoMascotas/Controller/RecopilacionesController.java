package com.MonitoreoMascotas.Controller;

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

import com.MonitoreoMascotas.Dto.Mensaje;
import com.MonitoreoMascotas.Dto.RecopilacionesDto;
import com.MonitoreoMascotas.Entity.Recopilaciones;
import com.MonitoreoMascotas.Service.RecopilacionesService;

@RestController
@RequestMapping("/recopilaciones")
@CrossOrigin(origins = "http://localhost:3000")
public class RecopilacionesController {
	
	@Autowired
	RecopilacionesService recopilacionesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Recopilaciones>> lista() {
		List<Recopilaciones> list = recopilacionesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping(produces = "application/json")
	public ResponseEntity<?> crear(@RequestBody @Validated RecopilacionesDto recopilacionesDto) {
		Recopilaciones recopilacion= new Recopilaciones();
		recopilacion.setLatitud(recopilacionesDto.getLatitud());
		recopilacion.setLongitud(recopilacionesDto.getLongitud());
		recopilacion.setTemperatura(recopilacionesDto.getTemperatura());
		recopilacion.setDispositivos(recopilacionesDto.getDispositivos());
		recopilacionesService.guardar(recopilacion);
		return new ResponseEntity<>(new Mensaje("Recopilacion creada"), HttpStatus.OK);
	}

}
