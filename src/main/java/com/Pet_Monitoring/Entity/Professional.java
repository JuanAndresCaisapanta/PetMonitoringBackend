package com.Pet_Monitoring.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
	private int id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String last_name;
	
	@NotNull
	private String address;
	
	@NotNull
	private String phone;
	
	@NotNull
	private Date creation_date;
	
	private Date update_date;
	
	@ManyToOne
	@JoinColumn(name = "profession_id")
	private Profession profession;
	
	@ManyToOne
	@JoinColumn(name="pet_id")
	private Pet pet;

}
