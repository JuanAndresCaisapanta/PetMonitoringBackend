package com.MonitoreoMascotas.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.MonitoreoMascotas.Security.Entity.Usuarios;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascotas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String color;
	private String raza;
	private Boolean esterilizacion;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaEsterilizacion;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "mascotas")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnoreProperties("mascotas")
	private List<Profesionales> profesionales;

	@ManyToOne(optional = false)
	@JoinColumn(name = "especies_id")
	private Especies especies;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "mascotas")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnoreProperties("mascotas")
	private List<Vacunas> vacunas;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "mascotas")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnoreProperties("mascotas")
	private List<Dispositivos> dispositivos;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "mascotas")
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnoreProperties("mascotas")
	private List<Recopilaciones> recopilaciones;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuarios_id")
	@JsonIgnoreProperties("mascotas")
	private Usuarios usuarios;

}
