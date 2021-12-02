package com.MonitoreoMascotas.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MonitoreoMascotas.Dto.DispositivosDto;
import com.MonitoreoMascotas.Dto.Mensaje;
import com.MonitoreoMascotas.Entity.Dispositivos;
import com.MonitoreoMascotas.Service.DispositivosService;

@RestController
@RequestMapping("/dispositivos")
@CrossOrigin(origins = "http://localhost:3000")
public class DispositivosController {

	@Autowired
	DispositivosService dispositivosService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Dispositivos>> lista() {
		List<Dispositivos> list = dispositivosService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listaid/{id}")
	public ResponseEntity<Dispositivos> getById(@PathVariable("id") int id) {
		if (!dispositivosService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Dispositivos dispositivo = dispositivosService.getOne(id).get();
		return new ResponseEntity<>(dispositivo, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listanombre/{nombre}")
	public ResponseEntity<Dispositivos> getByNombre(@PathVariable("nombre") String nombre) {
		if (!dispositivosService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Dispositivos dispositivo = dispositivosService.getByNombre(nombre).get();
		return new ResponseEntity<>(dispositivo, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> crear(@RequestBody @Validated DispositivosDto dispositivosDto) {
		if (StringUtils.isBlank(dispositivosDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Dispositivos dispositivo = new Dispositivos();
		dispositivo.setNombre(dispositivosDto.getNombre());
		dispositivo.setMarca(dispositivosDto.getMarca());
		dispositivo.setFabricante(dispositivosDto.getFabricante());
		dispositivo.setObservacion(dispositivosDto.getObservacion());
		dispositivo.setMascotas(dispositivosDto.getMascotas());
		dispositivosService.guardar(dispositivo);
		return new ResponseEntity<>(new Mensaje("Dispositivo creado"), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody DispositivosDto dispositivosDto) {
		if (!dispositivosService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		if (StringUtils.isBlank(dispositivosDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		Dispositivos dispositivo = dispositivosService.getOne(id).get();
		dispositivo.setNombre(dispositivosDto.getNombre());
		dispositivo.setMarca(dispositivosDto.getMarca());
		dispositivo.setFabricante(dispositivosDto.getFabricante());
		dispositivo.setObservacion(dispositivosDto.getObservacion());
		dispositivo.setMascotas(dispositivosDto.getMascotas());
		dispositivosService.actualizar(dispositivo);
		return new ResponseEntity<>(new Mensaje("Dispostivo actualizado"), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
		if (!dispositivosService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		dispositivosService.eliminar(id);
		return new ResponseEntity<>(new Mensaje("Dispositivo borrado"), HttpStatus.OK);
	}
	
}
