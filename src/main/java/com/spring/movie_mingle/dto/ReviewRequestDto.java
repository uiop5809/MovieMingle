package com.spring.movie_mingle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private Long userId;
    private Long movieId;
    private int rating;
    private String review;
}
