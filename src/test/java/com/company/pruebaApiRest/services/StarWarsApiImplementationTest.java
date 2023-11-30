package com.company.pruebaApiRest.services;

import com.company.pruebaApiRest.model.ApiResponseDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StarWarsApiImplementationTest {

    private IStarWarsApi implementation;

    private HttpClient httpClient;

    @BeforeEach
    void setUp() {
        httpClient = mock(HttpClient.class);
        implementation = new StarWarsApiImplementation(httpClient);
    }

    @SneakyThrows
    @Test
    void test_callApi_returnOK() {
        // init
        String body = "{\"title\": \"one\",\"episode_id\": 0,\"release_date\": \"1977-05-25\"}";
        HttpResponse httpResponse = mock(HttpResponse.class);

        ApiResponseDto expected = new ApiResponseDto();
        expected.setTitle("one");

       
        when(httpResponse.body()).thenReturn(body);
        when(httpClient.send(any(), any())).thenReturn(httpResponse);

        // run
        var result = implementation.callApi("0");

        // compare
        assertEquals(expected.getTitle(), result.getTitle());
    }
}