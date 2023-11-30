package com.company.pruebaApiRest.model;



import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDto {
  private int count;
  private String next;
  private String previous;
  
  
  private String title;
  private String episode_id;
  private String opening_crawl;
  private String director;
  private String producer;
  private String release_date;
  private List<String>  characters;
  private List<String>  planets;
  private List<String>  starships;
  private List<String>  vehicles;
  private List<String>  species;
  
  private String created;
  private String edited;
  private String url;
  
  private String detail; 
 

  
}
