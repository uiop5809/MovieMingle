package com.spring.movie_mingle.service;

import com.spring.movie_mingle.domain.Movie;
import com.spring.movie_mingle.domain.Review;
import com.spring.movie_mingle.domain.User;
import com.spring.movie_mingle.dto.ReviewRequestDto;
import com.spring.movie_mingle.dto.ReviewUpdateDto;
import com.spring.movie_mingle.repository.MovieRepository;
import com.spring.movie_mingle.repository.ReviewRepository;
import com.spring.movie_mingle.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public List<Review> findById(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        return reviewRepository.findByMovie(movie);
    }

    public Long createReview(ReviewRequestDto reviewRequestDto, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new IllegalStateException("로그인된 유저 정보가 없습니다.");
        }
        Movie movie = movieRepository.findById(reviewRequestDto.getMovieId()).orElseThrow();
        Review review = new Review(user, movie, reviewRequestDto.getRating(), reviewRequestDto.getReview());
        reviewRepository.save(review);

        return review.getId();
    }

    @Transactional
    public String updateReview(ReviewUpdateDto reviewUpdateDto) {
        Review review = reviewRepository.findById(reviewUpdateDto.getId()).orElseThrow();
        review.reviewUpdate(reviewUpdateDto.getRating(), reviewUpdateDto.getReview());
        return "업데이트 완료";
    }

    @Transactional
    public String deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.delete(review);
        return "삭제 완료";
    }
}
