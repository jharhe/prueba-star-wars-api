package com.company.pruebaApiRest.services;

import com.company.pruebaApiRest.model.ApiResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class StarWarsApiImplementation implements IStarWarsApi {
	
	  private final HttpClient httpClient;

	  public StarWarsApiImplementation() {
	    httpClient = HttpClient.newBuilder().build();
	  }

	  public StarWarsApiImplementation(HttpClient httpClient) {
	    this.httpClient = httpClient;
	  }

  public ApiResponseDto callApi(String idFilm) throws Exception {
    return this.buildResponseBody(this.getApiResponse(idFilm));
  }

  private HttpRequest buildRequest(String filmId) {
    return HttpRequest.newBuilder().GET()
      .uri(URI.create("https://swapi.dev/api/films/" + filmId))
      .build();
  }

  private String getApiResponse(String idFilm) throws Exception {
	  return httpClient
		      .send(this.buildRequest(idFilm), HttpResponse.BodyHandlers.ofString())
		      .body();
  }

  private ApiResponseDto buildResponseBody(String apiResponseBody) throws JsonProcessingException {
    return new ObjectMapper().readValue(apiResponseBody, ApiResponseDto.class);
  }
}
