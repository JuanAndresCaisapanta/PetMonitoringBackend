package com.Pet_Monitoring.Security.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Security.Entities.Role;
import com.Pet_Monitoring.Security.Enums.RoleName;
import com.Pet_Monitoring.Security.Repositories.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Optional<Role> getByRoleName(RoleName name) {
		return roleRepository.findByName(name);
	}
	
}
