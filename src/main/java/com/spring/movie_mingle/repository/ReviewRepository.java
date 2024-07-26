package com.spring.movie_mingle.repository;

import com.spring.movie_mingle.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
