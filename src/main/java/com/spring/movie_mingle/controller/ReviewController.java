package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.Review;
import com.spring.movie_mingle.dto.ReviewRequestDto;
import com.spring.movie_mingle.dto.ReviewUpdateDto;
import com.spring.movie_mingle.service.ReviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * Review id별 조회
     */
    @GetMapping("/list")
    public ResponseEntity<List<Review>> getReviewList(@RequestParam Long movieId) {
        return ResponseEntity.ok(reviewService.findById(movieId));
    }

    /**
     * Review 생성
     */
    @PostMapping("/write")
    public ResponseEntity<Long> createReview(@RequestBody ReviewRequestDto reviewRequestDto, HttpSession session) {
        return ResponseEntity.ok(reviewService.createReview(reviewRequestDto, session));
    }

    /**
     * Review 수정
     */
    @PostMapping("/update")
    public ResponseEntity<String> updateReview(@RequestBody ReviewUpdateDto reviewUpdateDto) {
        return ResponseEntity.ok(reviewService.updateReview(reviewUpdateDto));
    }

    /**
     * Review 삭제
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(@RequestParam Long id) {
        return ResponseEntity.ok(reviewService.deleteReview(id));
    }
}
