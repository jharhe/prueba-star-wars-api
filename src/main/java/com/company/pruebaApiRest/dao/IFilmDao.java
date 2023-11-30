package com.company.pruebaApiRest.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.pruebaApiRest.model.Film;

public interface IFilmDao extends CrudRepository<Film, Long>{

}
