package com.Pet_Monitoring.Entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


import com.Pet_Monitoring.Security.Entities.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String color_main;
	
	@NotNull
	private String color_secondary;
	
	@NotNull
	private float weight;
	
	@NotNull
	private String sex;
	
	private Boolean sterilization;
	
	private byte[] image;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date birth_date;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date creation_date;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date update_date;

	@OneToMany(mappedBy = "pet")
	private List<Establishment> establishment;
	
	@OneToMany(mappedBy = "pet")
	private List<Medicine> medicine;
	
	@OneToMany(mappedBy = "pet")
	private List<Professional> professional;
	
	@OneToMany(mappedBy = "pet")
	private List<Device> device; 

	@ManyToOne
	@JoinColumn(name = "breed_id")
	private Breed breed;

	@ManyToOne
	@JoinColumn(name = "users_id")
	@JsonIgnoreProperties({"role","pet","device","notification","image","creation_date","update_date","password"})
	private Users users;

}
