package com.jmorla.searchdemo.controller;

import com.jmorla.searchdemo.domain.Movie;
import com.jmorla.searchdemo.repository.MovieSearchRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieSearchRepository movieSearchRepository;

    public MovieController(MovieSearchRepository movieSearchRepository) {
        this.movieSearchRepository = movieSearchRepository;
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

}
