package com.MonitoreoMascotas.Security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Security.Entity.Roles;
import com.MonitoreoMascotas.Security.Enums.RolNombre;
import com.MonitoreoMascotas.Security.Repository.RolesRepository;

@Service
@Transactional
public class RolesService {
	
	 @Autowired
	    RolesRepository rolesRepository;

	    public Optional<Roles> getByRolNombre(RolNombre nombre){
	        return rolesRepository.findByNombre(nombre);
	    }

	    public void guardar(Roles roles){
	        rolesRepository.save(roles);
	    }

}
