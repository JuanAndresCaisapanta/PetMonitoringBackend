package com.MonitoreoMascotas.Security.Service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Entity.Mascotas;
import com.MonitoreoMascotas.Security.Entity.Usuarios;
import com.MonitoreoMascotas.Security.Repository.UsuariosRepository;

@Service
@Transactional
public class UsuariosService {
	 @Autowired
	    UsuariosRepository usuariosRepository;

	    public Optional<Usuarios> getByEmail(String email){
	        return usuariosRepository.findByEmail(email);
	    }

	    public boolean existsByEmail(String email){
	        return usuariosRepository.existsByEmail(email);
	    }

	    public void guardar(Usuarios usuario){
	        usuariosRepository.save(usuario);
	    }
	    public Optional<Usuarios> getOne(int id){
	        return usuariosRepository.findById(id);
	    }
}
