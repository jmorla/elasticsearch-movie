package com.jmorla.searchdemo.controller;

import com.jmorla.searchdemo.controller.dto.MovieSearchRequest;
import com.jmorla.searchdemo.domain.Movie;
import com.jmorla.searchdemo.repository.MovieSearchRepository;
import com.jmorla.searchdemo.service.MovieSearchService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieSearchRepository movieSearchRepository;
    private final MovieSearchService movieSearchService;

    public MovieController(
            MovieSearchRepository movieSearchRepository,
            MovieSearchService movieSearchService
    ) {
        this.movieSearchRepository = movieSearchRepository;
        this.movieSearchService = movieSearchService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") String id) {
        Movie movie = movieSearchRepository.findById(id)
        .orElseThrow(IllegalArgumentException::new);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(Pageable pageable) {
        List<Movie> movies = this.movieSearchRepository.findAll(pageable)
                .getContent();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/_search")
    public ResponseEntity searchMovie(Pageable pageable, MovieSearchRequest request) {
        List<Movie> movies = movieSearchService.searchAllMoviesMatchCriteria(request, pageable);
        return ResponseEntity.ok(movies);
    }

}
