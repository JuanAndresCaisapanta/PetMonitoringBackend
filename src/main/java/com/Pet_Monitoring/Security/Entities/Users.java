package com.Pet_Monitoring.Security.Entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import com.Pet_Monitoring.Entities.Device;
import com.Pet_Monitoring.Entities.Pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String last_name;

	@NotNull
	@Column(unique = true)
	private String email;

	@NotNull
	private String password;

	private String address;

	private String phone;

	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "users_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private  Collection<Role> role = new ArrayList<>();

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Pet> pet;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Device> device;
}
