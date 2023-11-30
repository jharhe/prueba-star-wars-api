package com.company.pruebaApiRest.response;

import java.util.List;

import com.company.pruebaApiRest.model.Film;

import lombok.Data;

@Data
public class FilmResponse {
	
	private List<Film> dtoFilm;

}
