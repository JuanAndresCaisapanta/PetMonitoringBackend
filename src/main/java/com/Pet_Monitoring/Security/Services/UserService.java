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

	public List<Users> readAllUser() {
		return (List<Users>) userRepository.findByOrderByIdAsc();
	}

	public Optional<Users> getByEmail(String userEmail) {
		return userRepository.findByEmail(userEmail);
	}

	public Optional<Users> getByUserId(Long userId) {
		return userRepository.findById(userId);
	}

	public boolean existsByUserEmail(String userEmail) {
		return userRepository.existsByEmail(userEmail);
	}

	public boolean existsByUserId(Long userId) {
		return userRepository.existsById(userId);
	}

	public void createUser(Users user) {
		userRepository.save(user);
	}

	public void updateUser(Users usuario) {
		userRepository.save(usuario);
	}
}
