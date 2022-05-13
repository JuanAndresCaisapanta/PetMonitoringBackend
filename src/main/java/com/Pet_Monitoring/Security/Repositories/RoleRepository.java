package com.Pet_Monitoring.Security.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Security.Entities.Role;
import com.Pet_Monitoring.Security.Enums.RoleName;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Optional<Role> findByName(RoleName name);
	
}
