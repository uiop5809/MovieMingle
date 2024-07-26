package com.spring.movie_mingle.repository;

import com.spring.movie_mingle.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
