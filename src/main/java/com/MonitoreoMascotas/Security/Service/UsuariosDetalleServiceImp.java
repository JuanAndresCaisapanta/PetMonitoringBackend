package com.MonitoreoMascotas.Security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MonitoreoMascotas.Security.Entity.Usuarios;
import com.MonitoreoMascotas.Security.Entity.UsuariosPrivilegios;

@Service
public class UsuariosDetalleServiceImp implements UserDetailsService{
	
	 @Autowired
	    UsuariosService usuariosService;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Usuarios usuario = usuariosService.getByEmail(email).get();
	        return UsuariosPrivilegios.build(usuario);
	    }

}
