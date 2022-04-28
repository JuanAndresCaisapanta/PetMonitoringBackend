package com.Pet_Monitoring.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.Pet_Monitoring.Security.Entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	private int id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String color;
	
	@NotNull
	private String race;
	
	@NotNull
	private float weight;
	
	@NotNull
	private String gender;
	
	@NotNull
	private Boolean sterilization;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birth_date;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date update_date;

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Establishment> establishment;
	
	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Medicine> medicine;
	
	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Professional> professional;

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Device_Data> device_data;

	@ManyToOne
	@JoinColumn(name = "species_id")
	private Species species;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Usuarios usuarios;

}
