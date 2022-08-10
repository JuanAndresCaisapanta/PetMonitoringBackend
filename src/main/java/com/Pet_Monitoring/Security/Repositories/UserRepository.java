package com.Pet_Monitoring.Security.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Security.Entities.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

	Optional<Users> findByEmail(String email);

	boolean existsByEmail(String email);
	
	List<Users>findByOrderByIdAsc();
	
	@Transactional
	@Modifying
	@Query("UPDATE Users u SET u.status = false WHERE u.id=:id")
	void softDeleteUser(@Param("id") Long id); 
	
}
