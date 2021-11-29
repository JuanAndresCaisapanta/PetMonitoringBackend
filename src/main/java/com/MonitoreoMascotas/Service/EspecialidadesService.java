package com.MonitoreoMascotas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Entity.Especialidades;
import com.MonitoreoMascotas.Repository.EspecialidadesRepository;

@Service
@Transactional
public class EspecialidadesService {
	
	 @Autowired
	    EspecialidadesRepository especialidadesRepository;

	    public List<Especialidades> lista(){
	        return (List<Especialidades>)especialidadesRepository.findAll();
	    }

	    public Optional<Especialidades> getOne(int id){
	        return especialidadesRepository.findById(id);
	    }

	    public Optional<Especialidades> getByNombre(String nombre){
	        return especialidadesRepository.findByNombre(nombre);
	    }

	    public void  save(Especialidades especialidades){
	        especialidadesRepository.save(especialidades);
	    }

	    public void delete(int id){
	        especialidadesRepository.deleteById(id);
	    }

	    public boolean existsById(int id){
	        return especialidadesRepository.existsById(id);
	    }

	    public boolean existsByNombre(String nombre){
	        return especialidadesRepository.existsByNombre(nombre);
	    }

}
