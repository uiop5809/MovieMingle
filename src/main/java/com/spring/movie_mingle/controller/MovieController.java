package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.Movie;
import com.spring.movie_mingle.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    private final String API_KEY = "2f41c6f48c1feee4ef28cc0ef3f0e998";
    private final String API_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";

    @GetMapping("/movies/weekly")
    @ResponseBody
    public ResponseEntity<List<Movie>> getWeeklyBoxOffice() {
        RestTemplate restTemplate = new RestTemplate();

        LocalDate today = LocalDate.now();
        LocalDate thisMonday = today.with(java.time.DayOfWeek.MONDAY);
        String targetDt = thisMonday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String url = String.format("%s?key=%s&targetDt=%s&weekGb=0", API_URL, API_KEY, targetDt);
        @SuppressWarnings("unchecked")
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Object> boxOfficeResult = (Map<String, Object>) response.get("boxOfficeResult");
        List<Map<String, Object>> weeklyBoxOfficeList = (List<Map<String, Object>>) boxOfficeResult.get("weeklyBoxOfficeList");

        for (Map<String, Object> movieData : weeklyBoxOfficeList) {
            Movie movie = new Movie();
            movie.setMovieCd((String) movieData.get("movieCd"));
            movie.setMovieNm((String) movieData.get("movieNm"));
            movie.setOpenDt((String) movieData.get("openDt"));
            movie.setSalesAmt(Long.parseLong((String) movieData.get("salesAmt")));
            movie.setSalesShare(Double.parseDouble((String) movieData.get("salesShare")));
            movie.setSalesAcc(Long.parseLong((String) movieData.get("salesAcc")));
            movie.setAudiCnt(Integer.parseInt((String) movieData.get("audiCnt")));
            movie.setAudiAcc(Integer.parseInt((String) movieData.get("audiAcc")));
            movie.setScrnCnt(Integer.parseInt((String) movieData.get("scrnCnt")));
            movie.setShowCnt(Integer.parseInt((String) movieData.get("showCnt")));
            movieRepository.save(movie);
        }

        return ResponseEntity.ok(movieRepository.findAll());
    }
}
