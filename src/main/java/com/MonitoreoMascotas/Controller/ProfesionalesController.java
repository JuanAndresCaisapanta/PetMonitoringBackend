package com.MonitoreoMascotas.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MonitoreoMascotas.Entity.Profesionales;
import com.MonitoreoMascotas.Service.ProfesionalesService;

@RestController
@RequestMapping("/profesionales")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfesionalesController {
	@Autowired
    ProfesionalesService profesionalesService;

	


	@GetMapping(produces = "application/json")
    public ResponseEntity<List<Profesionales>> list(){
        List<Profesionales> list = profesionalesService.lista();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
