package com.MonitoreoMascotas.Security.Entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.MonitoreoMascotas.Entity.Dispositivos;
import com.MonitoreoMascotas.Entity.Mascotas;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	private String nombre;
	@NotNull
	private String apellido;
	@NotNull
	private String email;
	@NotNull
	private String password;

	private String direccion;

	private String telefono;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Roles> roles = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "usuarios")
	@JsonIgnoreProperties("usuarios")
	private List<Mascotas> mascotas;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "usuarios")
	@JsonIgnoreProperties("usuarios")
	private List<Dispositivos> dispositivos;
}
