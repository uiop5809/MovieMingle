package com.spring.movie_mingle.repository;

import com.spring.movie_mingle.domain.Movie;
import com.spring.movie_mingle.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovie(Movie movie);
}
