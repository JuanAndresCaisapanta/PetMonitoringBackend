package com.Pet_Monitoring.Security.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Pet_Monitoring.Security.Entities.Users;
import com.Pet_Monitoring.Security.Repositories.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<Users> read() {
		return (List<Users>) userRepository.findAll();
	}

	public Optional<Users> getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<Users> getById(Long id) {
		return userRepository.findById(id);
	}

	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public boolean existsById(Long id) {
		return userRepository.existsById(id);
	}

	public void create(Users user) {
		userRepository.save(user);
	}

	public void update(Users usuario) {
		userRepository.save(usuario);
	}
}
