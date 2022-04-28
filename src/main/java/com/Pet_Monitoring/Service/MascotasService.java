package com.Pet_Monitoring.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Entity.Pet;
import com.Pet_Monitoring.Repository.MascotasRepository;

@Service
@Transactional
public class MascotasService {

	  @Autowired
	    MascotasRepository mascotasRepository;

	    public List<Pet> lista(){
	        return (List<Pet>)mascotasRepository.findAll();
	    }

	    public Optional<Pet> getOne(int id){
	        return mascotasRepository.findById(id);
	    }

	    public Optional<Pet> getByNombre(String nombre){
	        return mascotasRepository.findByNombre(nombre);
	    }

	    public void  guardar(Pet mascota){
	        mascotasRepository.save(mascota);
	    }
	    
	    public void  actualizar(Pet mascota){
	        mascotasRepository.save(mascota);
	    }

	    public void eliminar(int id){
	        mascotasRepository.deleteById(id);
	    }

	    public boolean existsById(int id){
	        return mascotasRepository.existsById(id);
	    }

	    public boolean existsByNombre(String nombre){
	        return mascotasRepository.existsByNombre(nombre);
	    }
	    public Optional<Pet> findByMascotas_id(int id){
	        return mascotasRepository.findById(id);
	    }
}
