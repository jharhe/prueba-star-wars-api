package com.company.pruebaApiRest.services;

import com.company.pruebaApiRest.dao.IFilmDao;
import com.company.pruebaApiRest.model.ApiResponseDto;
import com.company.pruebaApiRest.model.Film;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {

    @InjectMocks
    private FilmServiceImpl filmServiceImpl;

    @Spy
    private IFilmDao filmDao;

    @Spy
    private IStarWarsApi starWarsApi;

    @Test
    void test_searchById_returnOne() {
        // init
        Film expected = new Film();
        expected.setId(0L);
        expected.setTitle("one");

        when(filmDao.findById(anyLong())).thenReturn(Optional.of(expected));

        // run
        var result = filmServiceImpl.searchById(0L);

        // compare
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().getDtoFilmResponse().getDtoFilm().size());
        assertEquals(expected.getTitle(), result.getBody().getDtoFilmResponse().getDtoFilm().get(0).getTitle());
    }

    @Test
    void test_searchById_returnEmpty() {
        // init
        when(filmDao.findById(anyLong())).thenReturn(Optional.empty());

        // run
        var result = filmServiceImpl.searchById(0L);

        // compare
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @SneakyThrows
    @Test
    void test_searchApiById_returnOne() {
        // init
        Film expected = new Film();
        expected.setId(0L);
        expected.setTitle("one");

        ApiResponseDto apiResponse = new ApiResponseDto();
        apiResponse.setTitle(expected.getTitle());

        when(starWarsApi.callApi(anyString())).thenReturn(apiResponse);
        when(filmDao.save(any())).thenReturn(expected);

        // run
        var result = filmServiceImpl.searchApiById(0L);

        // compare
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().getDtoFilmResponse().getDtoFilm().size());
        assertEquals(expected.getTitle(), result.getBody().getDtoFilmResponse().getDtoFilm().get(0).getTitle());
    }

    @SneakyThrows
    @Test
    void test_searchApiById_returnEmpty() {
        // init
        ApiResponseDto apiResponse = new ApiResponseDto();
        apiResponse.setDetail("not found");

        when(starWarsApi.callApi(anyString())).thenReturn(apiResponse);

        // run
        var result = filmServiceImpl.searchApiById(0L);

        // compare
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }

    @SneakyThrows
    @Test
    void test_save_returnOne() {
        // init
        Film dtoFilm = new Film();
        dtoFilm.setTitle("one");

        Film expected = new Film();
        expected.setId(0L);
        expected.setTitle("one");

        when(filmDao.save(any())).thenReturn(expected);

        // run
        var result = filmServiceImpl.save(dtoFilm);

        // compare
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().getDtoFilmResponse().getDtoFilm().size());
        assertEquals(expected.getTitle(), result.getBody().getDtoFilmResponse().getDtoFilm().get(0).getTitle());
    }

    @SneakyThrows
    @Test
    void test_update_returnOne() {
        // init
        Film dtoFilm = new Film();
        dtoFilm.setTitle("one");

        Film expected = new Film();
        expected.setId(0L);
        expected.setTitle("two");

        when(filmDao.findById(anyLong())).thenReturn(Optional.of(new Film()));
        when(filmDao.save(any())).thenReturn(expected);

        // run
        var result = filmServiceImpl.update(dtoFilm, 0L);

        // compare
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().getDtoFilmResponse().getDtoFilm().size());
        assertEquals(expected.getTitle(), result.getBody().getDtoFilmResponse().getDtoFilm().get(0).getTitle());
    }


    @SneakyThrows
    @Test
    void test_delete_returnOK() {
        // init
        doNothing().when(filmDao).deleteById(anyLong());

        // run
        var result = filmServiceImpl.deleteById(0L);

        // compare
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(filmDao, times(1)).deleteById(0L);
    }

}