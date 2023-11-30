package com.company.pruebaApiRest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.pruebaApiRest.dao.IFilmDao;
import com.company.pruebaApiRest.model.ApiResponseDto;
import com.company.pruebaApiRest.model.Film;
import com.company.pruebaApiRest.response.FilmResponseRest;

@Service
public class FilmServiceImpl implements IFilmService{
	
	@Autowired
	private IFilmDao dtoFilmDao;
	
	@Autowired
	private  IStarWarsApi starWarsApi;
	
	

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<FilmResponseRest> search() {
		
		FilmResponseRest response = new FilmResponseRest();
		
		try {
			
			List<Film> dtoFilm = (List<Film>) dtoFilmDao.findAll();
			
			response.getDtoFilmResponse().setDtoFilm(dtoFilm);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<FilmResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<FilmResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<FilmResponseRest> searchById(Long id) {
		
		FilmResponseRest response = new FilmResponseRest();
		List<Film> list = new ArrayList<>();
		
		try {
			
			Optional<Film> dtoFilm = dtoFilmDao.findById(id);
			
			if (dtoFilm.isPresent()) {
				list.add(dtoFilm.get());
				response.getDtoFilmResponse().setDtoFilm(list);
				response.setMetadata("Respuesta ok", "00", "Film encontrada");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Film no encontrada");
				return new ResponseEntity<FilmResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al consultar por id");
			e.getStackTrace();
			return new ResponseEntity<FilmResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<FilmResponseRest>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional(readOnly = false)
	public ResponseEntity<FilmResponseRest> searchApiById(Long id) throws Exception {
		FilmResponseRest response = new FilmResponseRest();
		List<Film> list = new ArrayList<>();
		
		
		
	try {	
		
		ApiResponseDto apiResults = starWarsApi.callPlanetApi(id+"");		

	    if ( apiResults.getDetail() !=null ) {	    
	    	response.setMetadata("Respuesta nok", "-1", "Film no encontrado");
			return new ResponseEntity<FilmResponseRest>(response, HttpStatus.NO_CONTENT);
	    }else {
	    	Film dto= new  Film(); 
	    	dto.setEpisode_id(apiResults.getEpisode_id());
	    	dto.setTitle(apiResults.getTitle());
	    	dto.setRelease_date(apiResults.getRelease_date());	    	
			
	    	Film dtoFilmSaved = dtoFilmDao.save(dto);			
			if (dtoFilmSaved != null) {
				list.add(dtoFilmSaved);
				response.getDtoFilmResponse().setDtoFilm(list);
				response.setMetadata("Respuesta ok", "00", "Film guardado");
			}
	    }		
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al consultar api por id");
			e.getStackTrace();
			return new ResponseEntity<FilmResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<FilmResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<FilmResponseRest> save(Film dtoFilm) {
		
		FilmResponseRest response = new FilmResponseRest();
		List<Film> list = new ArrayList<>();
		
		try {
			
			Film dtoFilmSaved = dtoFilmDao.save(dtoFilm);
			
			if (dtoFilmSaved != null) {
				list.add(dtoFilmSaved);
				response.getDtoFilmResponse().setDtoFilm(list);
				response.setMetadata("Respuesta ok", "00", "Film guardado");
			} else {
				response.setMetadata("Respuesta nok", "-1", "Film no guardado");
				return new ResponseEntity<FilmResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al grabar Film");
			e.getStackTrace();
			return new ResponseEntity<FilmResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<FilmResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<FilmResponseRest> update(Film dtoFilm, Long id) {
		FilmResponseRest response = new FilmResponseRest();
		List<Film> list = new ArrayList<>();
		
		try {
			
			Optional<Film> dtoFilmSearch = dtoFilmDao.findById(id);
			
			if (dtoFilmSearch.isPresent()) {
				// se procederá a actualizar el registro
				dtoFilmSearch.get().setEpisode_id(dtoFilm.getEpisode_id());
				dtoFilmSearch.get().setTitle(dtoFilm.getTitle());
				dtoFilmSearch.get().setRelease_date(dtoFilm.getRelease_date());
				
				Film dtoFilmToUpdate = dtoFilmDao.save(dtoFilmSearch.get());
				
				if (dtoFilmToUpdate != null) {
					list.add(dtoFilmToUpdate);
					response.getDtoFilmResponse().setDtoFilm(list);
					response.setMetadata("Respuesta ok", "00", "Film actualizada");
				} else {
					response.setMetadata("Respuesta nok", "-1", "Film no actualizada");
					return new ResponseEntity<FilmResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
				
				
			} else {
				response.setMetadata("Respuesta nok", "-1", "Film no encontrada");
				return new ResponseEntity<FilmResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar Film");
			e.getStackTrace();
			return new ResponseEntity<FilmResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<FilmResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<FilmResponseRest> deleteById(Long id) {
		
		FilmResponseRest response = new FilmResponseRest();
		
		try {
			
			dtoFilmDao.deleteById(id);
			response.setMetadata("respuesta ok", "00", "Registro eliminado");
			
			
		} catch (Exception e) {
			
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar");
			e.getStackTrace();
			return new ResponseEntity<FilmResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
			
		}
		
		return new ResponseEntity<FilmResponseRest>(response, HttpStatus.OK);
		
	}
	
	

}
