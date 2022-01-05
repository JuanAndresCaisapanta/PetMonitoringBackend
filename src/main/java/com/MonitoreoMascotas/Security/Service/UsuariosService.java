package com.MonitoreoMascotas.Security.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.MonitoreoMascotas.Security.Entity.Usuarios;
import com.MonitoreoMascotas.Security.Repository.UsuariosRepository;

@Service
@Transactional
public class UsuariosService {
	@Autowired
	UsuariosRepository usuariosRepository;

	public Optional<Usuarios> getByEmail(String email) {
		return usuariosRepository.findByEmail(email);
	}

	public Optional<Usuarios> getById(int id) {
		return usuariosRepository.findById(id);
	}

	public boolean existsByEmail(String email) {
		return usuariosRepository.existsByEmail(email);
	}

	public boolean existsById(int id) {
		return usuariosRepository.existsById(id);
	}

	public void guardar(Usuarios usuario) {
		usuariosRepository.save(usuario);
	}

	public Optional<Usuarios> getOne(int id) {
		return usuariosRepository.findById(id);
	}

	public void actualizar(Usuarios usuario) {
		usuariosRepository.save(usuario);
	}
}
