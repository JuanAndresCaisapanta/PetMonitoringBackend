package com.MonitoreoMascotas.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MonitoreoMascotas.Security.Entity.Usuarios;

@Repository
public interface UsuariosRepository  extends JpaRepository<Usuarios, Integer>{
	 Optional<Usuarios> findByEmail(String email);
	    boolean existsByEmail(String email);
}
