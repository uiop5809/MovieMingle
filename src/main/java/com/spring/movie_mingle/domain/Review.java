package com.spring.movie_mingle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int review_id;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private int movie_id;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String review;

    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at;

    @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updated_at;
}
