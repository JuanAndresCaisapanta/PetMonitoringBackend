package com.Pet_Monitoring.Security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Security.Entity.Roles;
import com.Pet_Monitoring.Security.Enums.RolNombre;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
	Optional<Roles> findByNombre(RolNombre nombre);
}
