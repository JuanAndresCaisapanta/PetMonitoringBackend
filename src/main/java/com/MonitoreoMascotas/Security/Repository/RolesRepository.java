package com.MonitoreoMascotas.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MonitoreoMascotas.Security.Entity.Roles;
import com.MonitoreoMascotas.Security.Enums.RolNombre;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
	Optional<Roles> findByNombre(RolNombre nombre);
}
