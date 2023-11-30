package com.company.pruebaApiRest.services;

import org.springframework.http.ResponseEntity;

import com.company.pruebaApiRest.model.Film;
import com.company.pruebaApiRest.response.FilmResponseRest;

public interface IFilmService {
	
	public ResponseEntity<FilmResponseRest> search();
	public ResponseEntity<FilmResponseRest> searchById(Long id);
	public ResponseEntity<FilmResponseRest> save(Film dtoFilm);
	public ResponseEntity<FilmResponseRest> update(Film dtoFilm, Long id);
	public ResponseEntity<FilmResponseRest> deleteById(Long id);
	ResponseEntity<FilmResponseRest> searchApiById(Long id) throws Exception;

}
