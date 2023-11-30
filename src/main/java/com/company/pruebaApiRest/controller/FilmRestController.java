package com.company.pruebaApiRest.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.company.pruebaApiRest.model.Film;
import com.company.pruebaApiRest.response.FilmResponseRest;
import com.company.pruebaApiRest.services.IFilmService;



@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class FilmRestController {
	
	@Autowired
	private IFilmService service;
	
	
	
	
	
	/**
	 * get all the films
	 * @return
	 */
	@GetMapping("/films")
	public ResponseEntity<FilmResponseRest> searchCategories() {
		
		ResponseEntity<FilmResponseRest> response = service.search();
		return response;
	}
	
	/**
	 * get films by id
	 * @param id
	 * @return	 
	 */
	@GetMapping("/films/{id}")
	public ResponseEntity<FilmResponseRest> searchFilmsById(@PathVariable Long id){			
		ResponseEntity<FilmResponseRest> response = service.searchById(id);
		return response;
	}
	
	/**
	 * get films api by id
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/films/api/{id}")
	public ResponseEntity<FilmResponseRest> searchFilmsApiById(@PathVariable Long id) throws Exception {
		
		
		ResponseEntity<FilmResponseRest> response = service.searchApiById(id);
		return response;
	}
	
	/**
	 * save films
	 * @param Film
	 * @return
	 */
	@PostMapping("/films")
	public ResponseEntity<FilmResponseRest> save(@RequestBody Film dtoFilm) {
		
		ResponseEntity<FilmResponseRest> response = service.save(dtoFilm);
		return response;
	}
	
	/**
	 * update films
	 * @param dtoFilm
	 * @param id
	 * @return
	 */
	@PutMapping("/films/{id}")
	public ResponseEntity<FilmResponseRest> update(@RequestBody Film dtoFilm, @PathVariable Long id) {
		
		ResponseEntity<FilmResponseRest> response = service.update(dtoFilm, id);
		return response;
	}
	
	/**
	 * delete categorie
	 * @param id
	 * @return
	 */
	@DeleteMapping("/films/{id}")
	public ResponseEntity<FilmResponseRest> delete(@PathVariable Long id) {
		
		ResponseEntity<FilmResponseRest> response = service.deleteById(id);
		return response;
	}
	
	

}
