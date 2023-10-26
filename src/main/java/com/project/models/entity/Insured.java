package com.project.models.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "asegurados")
@Data
public class Insured {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo_identificacion")
	private Long typeId;
	
	@Column(name = "numero_identificacion")
	private Long numberId;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "apellido")
	private String lastname;
	
	@Column(name = "genero")
	private Long gender;
	
	@Column(name = "fecha_nacimiento")
	private LocalDate birthdate;
}
