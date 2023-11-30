package com.company.pruebaApiRest.services;

import com.company.pruebaApiRest.model.ApiResponseDto;

public interface IStarWarsApi {
	  ApiResponseDto callPlanetApi(String planetName) throws Exception;
	}
