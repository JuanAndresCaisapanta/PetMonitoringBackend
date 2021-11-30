package com.MonitoreoMascotas.Controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import com.MonitoreoMascotas.Dto.EspecialidadesDto;
import com.MonitoreoMascotas.Dto.Mensaje;
import com.MonitoreoMascotas.Entity.Especialidades;
import com.MonitoreoMascotas.Service.EspecialidadesService;

@RestController
@RequestMapping("/especialidades")
@CrossOrigin(origins = "http://localhost:3000")
public class EspecialidadesController {
	@Autowired
	EspecialidadesService especialidadesService;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Especialidades>> list() {
		List<Especialidades> list = especialidadesService.lista();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

//    @GetMapping("{id}")
//    public ResponseEntity<Mascotas> getById(@PathVariable("id") int id){
//        if(!mascotasService.existsById(id))
//            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        Mascotas mascota = mascotasService.getOne(id).get();
//        return new ResponseEntity(mascota, HttpStatus.OK);
//    }
//
//    @GetMapping("{nombre}")
//    public ResponseEntity<Mascotas> getByNombre(@PathVariable("nombre") String nombre){
//        if(!mascotasService.existsByNombre(nombre))
//            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        Mascotas mascota = mascotasService.getByNombre(nombre).get();
//        return new ResponseEntity(mascota, HttpStatus.OK);
//    }
//
	@PostMapping(produces = "application/json")
	public ResponseEntity<?> crear(@RequestBody @Validated EspecialidadesDto especialidadesDto) {
		if (StringUtils.isBlank(especialidadesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
		if (especialidadesService.existsByNombre(especialidadesDto.getNombre()))
			return new ResponseEntity<>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		Especialidades especialidad = new Especialidades(especialidadesDto.getNombre(),especialidadesDto.getProfesionales());
		especialidadesService.save(especialidad);
		return new ResponseEntity<>(new Mensaje("Especialidad creada"), HttpStatus.OK);
	}
//
//    @PutMapping("{id}")
//    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody MascotasDto mascotasDto){
//        if(!mascotasService.existsById(id))
//            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        if(mascotasService.existsByNombre(mascotasDto.getNombre()) && mascotasService.getByNombre(mascotasDto.getNombre()).get().getId() != id)
//            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
//        if(StringUtils.isBlank(mascotasDto.getNombre()))
//            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
//      
//
//        Mascotas mascota = mascotasService.getOne(id).get();
//        mascota.setNombre(mascotasDto.getNombre());
//        mascota.setColor(mascotasDto.getColor());
//        mascota.setRaza(mascotasDto.getRaza());
//        mascota.setEsterilizacion(mascotasDto.getEsterilizacion());
//        mascota.setFechaEsterilizacion(mascotasDto.getFechaEsterilizacion());
//        mascota.setFechaNacimiento(mascotasDto.getFechaNacimiento());
//        mascotasService.save(mascota);
//        return new ResponseEntity(new Mensaje("mascota actualizada"), HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> delete(@PathVariable("id")int id){
//        if(!mascotasService.existsById(id))
//            return  new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
//        mascotasService.delete(id);
//        return new ResponseEntity(new Mensaje("mascota borrada"), HttpStatus.OK);
//    }

}
