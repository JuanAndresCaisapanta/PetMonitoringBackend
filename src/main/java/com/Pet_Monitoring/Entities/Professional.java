package com.Pet_Monitoring.Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String last_name;
	
	@NotNull
	private String address;
	
	@NotNull
	private String email;
	
	@NotNull
	private String cell_phone;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date creation_date;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date update_date;
	
	@ManyToOne
	@JoinColumn(name = "profession_id")
	private Profession profession;
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	@JsonIgnoreProperties({"establishment","medicine","professional","device","image"})
	private Pet pet;

}
