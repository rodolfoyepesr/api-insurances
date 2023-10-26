package com.project.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "primas")
@Data
public class Premium {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo")
	private Long code;
	
	@Column(name = "edad_minima")
	private int ageMin;
	
	@Column(name = "edad_maxima")
	private int ageMax;
	
	@Column(name = "porcentaje_prima")
	private Double premium;
	
	@ManyToOne
	@JoinColumn(name = "protection")
	private Protection protection;
}
