package com.MonitoreoMascotas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Entity.Mascotas;
import com.MonitoreoMascotas.Repository.MascotasRepository;

@Service
@Transactional
public class MascotasService {

	  @Autowired
	    MascotasRepository mascotasRepository;

	    public List<Mascotas> list(){
	        return (List<Mascotas>)mascotasRepository.findAll();
	    }

	    public Optional<Mascotas> getOne(int id){
	        return mascotasRepository.findById(id);
	    }

	    public Optional<Mascotas> getByNombre(String nombre){
	        return mascotasRepository.findByNombre(nombre);
	    }

	    public void  save(Mascotas mascota){
	        mascotasRepository.save(mascota);
	    }

	    public void delete(int id){
	        mascotasRepository.deleteById(id);
	    }

	    public boolean existsById(int id){
	        return mascotasRepository.existsById(id);
	    }

	    public boolean existsByNombre(String nombre){
	        return mascotasRepository.existsByNombre(nombre);
	    }
	
}
