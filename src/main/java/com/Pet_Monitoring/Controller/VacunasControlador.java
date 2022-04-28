package com.Pet_Monitoring.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.Pet_Monitoring.Dto.Mensaje;
import com.Pet_Monitoring.Dto.VacunasDto;
import com.Pet_Monitoring.Entity.Medicine;
import com.Pet_Monitoring.Service.VacunasService;

@RestController
@RequestMapping("/vacunas")
@CrossOrigin(origins = "http://localhost:3000")
public class VacunasControlador {

	@Autowired
	VacunasService vacunasService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Medicine>> lista() {
		List<Medicine> list = vacunasService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listaid/{id}")
	public ResponseEntity<Medicine> getById(@PathVariable("id") int id) {
		if (!vacunasService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Medicine vacuna = vacunasService.getOne(id).get();
		return new ResponseEntity<>(vacuna, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/listanombre/{nombre}")
	public ResponseEntity<Medicine> getByNombre(@PathVariable("nombre") String nombre) {
		if (!vacunasService.existsByNombre(nombre))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Medicine vacuna = vacunasService.getByNombre(nombre).get();
		return new ResponseEntity<>(vacuna, HttpStatus.OK);
	}

	/*
	 * @PostMapping(produces = "application/json") public ResponseEntity<?>
	 * crear(@RequestBody @Validated VacunasDto vacunasDto) throws ParseException {
	 * if (StringUtils.isBlank(vacunasDto.getFabricador())) return new
	 * ResponseEntity<>(new Mensaje("el nombre es obligatorio"),
	 * HttpStatus.BAD_REQUEST); Medicine vacuna = new Medicine();
	 * vacuna.setFabricador(vacunasDto.getFabricador());
	 * vacuna.setLote(vacunasDto.getLote());
	 * vacuna.setAplicador(vacunasDto.getAplicador());
	 * vacuna.setFechaFabricacion(vacunasDto.getFechaFabricacion());
	 * vacuna.setFechaCaducidad(vacunasDto.getFechaCaducidad());
	 * vacuna.setFechaAplicacion(vacunasDto.getFechaAplicacion());
	 * vacuna.setFechaRevacuna(vacunasDto.getFechaRevacuna());
	 * vacuna.setMascotas(vacunasDto.getMascotas()); vacunasService.guardar(vacuna);
	 * return new ResponseEntity<>(new Mensaje("Vacuna creada"), HttpStatus.OK); }
	 * 
	 * @PutMapping("{id}") public ResponseEntity<?> actualizar(@PathVariable("id")
	 * int id, @RequestBody VacunasDto vacunasDto) throws ParseException { if
	 * (!vacunasService.existsById(id)) return new ResponseEntity<>(new
	 * Mensaje("no existe"), HttpStatus.NOT_FOUND); if
	 * (StringUtils.isBlank(vacunasDto.getFabricador())) return new
	 * ResponseEntity<>(new Mensaje("el nombre es obligatorio"),
	 * HttpStatus.BAD_REQUEST); Medicine vacuna = vacunasService.getOne(id).get();
	 * vacuna.setFabricador(vacunasDto.getFabricador());
	 * vacuna.setLote(vacunasDto.getLote());
	 * vacuna.setAplicador(vacunasDto.getAplicador());
	 * vacuna.setFechaFabricacion(vacunasDto.getFechaFabricacion());
	 * vacuna.setFechaCaducidad(vacunasDto.getFechaCaducidad());
	 * vacuna.setFechaAplicacion(vacunasDto.getFechaAplicacion());
	 * vacuna.setFechaRevacuna(vacunasDto.getFechaRevacuna());
	 * vacuna.setMascotas(vacunasDto.getMascotas());
	 * vacunasService.actualizar(vacuna); return new ResponseEntity<>(new
	 * Mensaje("Vacuna actualizada"), HttpStatus.OK); }
	 */

	@DeleteMapping("{id}")
	public ResponseEntity<?> borrar(@PathVariable("id") int id) {
		if (!vacunasService.existsById(id))
			return new ResponseEntity<>(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		vacunasService.eliminar(id);
		return new ResponseEntity<>(new Mensaje("Vacuna borrada"), HttpStatus.OK);
	}

	public Date fecha(Date dia) throws ParseException {
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date diaFormateado = formato.parse(formato.format(dia));
		return diaFormateado;
	}

}
