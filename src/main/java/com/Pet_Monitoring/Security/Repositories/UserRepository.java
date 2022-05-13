package com.Pet_Monitoring.Security.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Pet_Monitoring.Security.Entities.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	Optional<Users> findByEmail(String email);

	boolean existsByEmail(String email);
}
