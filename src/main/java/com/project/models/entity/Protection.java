package com.project.models.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "amparos")
@Data
public class Protection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo")
	private Long code;
	
	@Column(name = "nombre")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "protection")
	private List<Premium> premiums;
}
