package com.spring.movie_mingle.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String movieCd;

    @Column(nullable = false)
    private String movieNm;

    @Column(nullable = false)
    private String openDt;

    @Column(nullable = false)
    private Long salesAmt;

    @Column(nullable = false)
    private Double salesShare;

    @Column(nullable = false)
    private Long salesAcc;

    @Column(nullable = false)
    private Integer audiCnt;

    @Column(nullable = false)
    private Integer audiAcc;

    @Column(nullable = false)
    private Integer scrnCnt;

    @Column(nullable = false)
    private Integer showCnt;
}
