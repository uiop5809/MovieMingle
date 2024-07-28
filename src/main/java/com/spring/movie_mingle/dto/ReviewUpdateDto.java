package com.spring.movie_mingle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewUpdateDto {
    private Long id;
    private int rating;
    private String review;
}
