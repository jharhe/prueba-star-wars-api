package com.company.pruebaApiRest.services;

import com.company.pruebaApiRest.model.ApiResponseDto;

public interface IStarWarsApi {
	  ApiResponseDto callApi(String idFilm) throws Exception;
	}
