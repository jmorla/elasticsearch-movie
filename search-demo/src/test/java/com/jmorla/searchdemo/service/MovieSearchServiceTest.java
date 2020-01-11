package com.jmorla.searchdemo.service;

import com.jmorla.searchdemo.controller.dto.MovieSearchRequest;
import com.jmorla.searchdemo.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
public class MovieSearchServiceTest {

    @Autowired
    private MovieSearchService movieSearchService;


    @Test
    public void mustReturnAllMovieMatchCriteria() {
        MovieSearchRequest request = new MovieSearchRequest();
        request.setTitle("transfo");
        List<Movie> movies = movieSearchService.searchAllMoviesMatchCriteria(request, PageRequest.of(0, 10));
        Assertions.assertFalse(movies.isEmpty());
        System.out.println(movies);
    }
}
