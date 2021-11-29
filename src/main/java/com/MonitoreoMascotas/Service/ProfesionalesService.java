package com.MonitoreoMascotas.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Entity.Profesionales;
import com.MonitoreoMascotas.Repository.ProfesionalesRepository;
@Service
@Transactional
public class ProfesionalesService {
	@Autowired
    ProfesionalesRepository profesionalesRepository;

    public List<Profesionales> lista(){
        return (List<Profesionales>)profesionalesRepository.findAll();
    }

    public Optional<Profesionales> getOne(int id){
        return profesionalesRepository.findById(id);
    }

    public Optional<Profesionales> getByNombre(String nombre){
        return profesionalesRepository.findByNombre(nombre);
    }

    public void  save(Profesionales profesionales){
    	profesionalesRepository.save(profesionales);
    }

    public void delete(int id){
    	profesionalesRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return profesionalesRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return profesionalesRepository.existsByNombre(nombre);
    }
}
