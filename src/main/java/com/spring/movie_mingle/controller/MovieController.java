package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.Movie;
import com.spring.movie_mingle.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MovieController {

    private final MovieService movieService;

    /**
     * Movie DB 저장
     */
    @PostMapping("/movies/popular")
    public ResponseEntity<Long> getPopularMovies() {
        return ResponseEntity.ok(movieService.insertMovie());
    }

    /**
     * Movie 전체 조회
     */
    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getMovieAll() {
       return ResponseEntity.ok(movieService.findAll());
    }

    /**
     * Movie 상세 조회
     */
    @GetMapping("/movies/detail")
    public ResponseEntity<Movie> getMovieById(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    /**
     * Movie 최신순 정렬
     */

    /**
     * Movie 평점순 정렬
     */
}