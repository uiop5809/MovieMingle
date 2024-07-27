package com.spring.movie_mingle.service;

import com.spring.movie_mingle.domain.Movie;
import com.spring.movie_mingle.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class MovieService {
    private final MovieRepository movieRepository;

    private final String TMDB_API_KEY = "0e1a71e289465f90209535b3a6039dc2";
    private final String TMDB_API_URL = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=ko-KR&page=1&sort_by=popularity.desc&api_key=" + TMDB_API_KEY;

    public Long insertMovie() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(TMDB_API_URL, Map.class);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
        Movie movie = new Movie();

        for (Map<String, Object> movieData : results) {
            movie.setTitle((String) movieData.get("title"));
            movie.setPosterUrl("https://image.tmdb.org/t/p/w500" + (String) movieData.get("poster_path"));
            movie.setOverview((String) movieData.get("overview"));
            movie.setVoteAverage(Double.parseDouble(movieData.get("vote_average").toString()));
            movie.setReleaseDate((String) movieData.get("release_date"));
            movieRepository.save(movie);
        }
        return movie.getId();
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow();
    }


}
