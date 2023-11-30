package com.company.pruebaApiRest.model;

import java.io.Serializable;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import lombok.Data;

@Data
@Entity
@Table(name="films")
public class Film implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4310027227752446841L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String episode_id;
	private String title;
	private String release_date;

}
