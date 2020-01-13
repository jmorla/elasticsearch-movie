package com.jmorla.searchdemo.controller;

import com.jmorla.searchdemo.controller.dto.MovieSearchRequest;
import com.jmorla.searchdemo.domain.Movie;
import com.jmorla.searchdemo.repository.MovieSearchRepository;
import com.jmorla.searchdemo.service.MovieSearchService;
import org.elasticsearch.search.aggregations.Aggregation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping
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

    @GetMapping(value = "/movies/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") String id) {
        Movie movie = movieSearchRepository.findById(id)
        .orElseThrow(IllegalArgumentException::new);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(Pageable pageable) {
        List<Movie> movies = this.movieSearchRepository.findAll(pageable)
                .getContent();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "/movies/_search")
    public ResponseEntity<List<Movie>> searchMovie(Pageable pageable, MovieSearchRequest request) {
        List<Movie> movies = movieSearchService.searchAllMoviesMatchCriteria(request, pageable);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/directors")
    public ResponseEntity<List<String>> getAllDirectors() {
        List<String> directors = movieSearchService.getAllDirectors();
        return ResponseEntity.ok(directors);
    }

    @GetMapping("/distributors")
    public ResponseEntity<List<String>> getAllDistributors() {
        List<String> distributors = movieSearchService.getAllDistributor();
        return ResponseEntity.ok(distributors);
    }

    @GetMapping("/movie-types")
    public ResponseEntity<List<String>> getAllTypes() {
        List<String> movieTypes = movieSearchService.getAllTypes();
        return ResponseEntity.ok(movieTypes);
    }

}
