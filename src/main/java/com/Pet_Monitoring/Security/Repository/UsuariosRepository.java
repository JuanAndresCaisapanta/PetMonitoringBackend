package com.Pet_Monitoring.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Security.Entity.Usuarios;

@Repository
public interface UsuariosRepository  extends JpaRepository<Usuarios, Integer>{
	 Optional<Usuarios> findByEmail(String email);
	    boolean existsByEmail(String email);
}
