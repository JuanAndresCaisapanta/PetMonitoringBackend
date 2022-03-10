package com.MonitoreoMascotas.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MonitoreoMascotas.Dto.MascotasDto;
import com.MonitoreoMascotas.Dto.Mensaje;
import com.MonitoreoMascotas.Entity.Mascotas;
import com.MonitoreoMascotas.Service.MascotasService;

@RestController
@RequestMapping("/mascotas")
@CrossOrigin
public class MascotasController {
	@Autowired
	MascotasService mascotasService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Mascotas>> lista() {
		List<Mascotas> list = mascotasService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/mascota/{id}")
	public ResponseEntity<Mascotas> getById(@PathVariable("id") int id) {
		if (!mascotasService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Mascotas mascota = mascotasService.findByMascotas_id(id).get();
		return new ResponseEntity(mascota, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listanombre/{nombre}")
	public ResponseEntity<Mascotas> getByNombre(@PathVariable("nombre") String nombre) {
		if (!mascotasService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Mascotas mascota = mascotasService.getByNombre(nombre).get();
		return new ResponseEntity(mascota, HttpStatus.OK);
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<?> guardar(@RequestBody MascotasDto mascotasDto) {
		if (StringUtils.isBlank(mascotasDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//        if(mascotasService.existsByNombre(mascotasDto.getNombre()))
//            return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Mascotas mascota = new Mascotas();
		mascota.setNombre(mascotasDto.getNombre());
		mascota.setColor(mascotasDto.getColor());
		mascota.setRaza(mascotasDto.getRaza());
		mascota.setGenero(mascotasDto.getGenero());
		mascota.setEsterilizacion(mascotasDto.getEsterilizacion());
		mascota.setFechaEsterilizacion(mascotasDto.getFechaEsterilizacion());
		mascota.setFechaNacimiento(mascotasDto.getFechaNacimiento());
		mascota.setEspecies(mascotasDto.getEspecies());
		mascota.setUsuarios(mascotasDto.getUsuarios());
		mascotasService.guardar(mascota);
		return new ResponseEntity<>(new Mensaje("Mascota creada"), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody MascotasDto mascotasDto) {
		if (!mascotasService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		if (mascotasService.existsByNombre(mascotasDto.getNombre())
				&& mascotasService.getByNombre(mascotasDto.getNombre()).get().getId() != id)
			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		if (StringUtils.isBlank(mascotasDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);

		Mascotas mascota = mascotasService.getOne(id).get();
		mascota.setNombre(mascotasDto.getNombre());
		mascota.setColor(mascotasDto.getColor());
		mascota.setRaza(mascotasDto.getRaza());
		mascota.setGenero(mascotasDto.getGenero());
		mascota.setEsterilizacion(mascotasDto.getEsterilizacion());
		mascota.setFechaEsterilizacion(mascotasDto.getFechaEsterilizacion());
		mascota.setFechaNacimiento(mascotasDto.getFechaNacimiento());
		mascota.setEspecies(mascotasDto.getEspecies());
		mascota.setUsuarios(mascotasDto.getUsuarios());
		mascotasService.actualizar(mascota);
		return new ResponseEntity<>(new Mensaje("Mascota actualizada"), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") int id) {
		if (!mascotasService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		mascotasService.eliminar(id);
		return new ResponseEntity<>(new Mensaje("Mascota borrada"), HttpStatus.OK);
	}

}
