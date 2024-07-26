package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.Movie;
import com.spring.movie_mingle.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    private final String TMDB_API_KEY = "0e1a71e289465f90209535b3a6039dc2";
    private final String TMDB_API_URL = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=ko-KR&page=1&sort_by=popularity.desc&api_key=" + TMDB_API_KEY;

    @GetMapping("/movies/popular")
    @ResponseBody
    public ResponseEntity<List<Movie>> getPopularMovies() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(TMDB_API_URL, Map.class);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

        for (Map<String, Object> movieData : results) {
            Movie movie = new Movie();
            movie.setTitle((String) movieData.get("title"));
            movie.setPosterUrl("https://image.tmdb.org/t/p/w500" + (String) movieData.get("poster_path"));
            movie.setOverview((String) movieData.get("overview"));
            movie.setVoteAverage(Double.parseDouble(movieData.get("vote_average").toString()));
            movie.setReleaseDate((String) movieData.get("release_date"));

            movieRepository.save(movie);
        }
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @GetMapping("/movies/{id}")
    @ResponseBody
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
