package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.Review;
import com.spring.movie_mingle.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepo;

    @GetMapping("/review/list")
    @ResponseBody
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
        List<Review> list = reviewRepo.findAll();
        result.put("code", "ok");
        result.put("data", list);
        return result;
    }

    @GetMapping("/review/detail/{review_id}")
    @ResponseBody
    public Map<String, Object> detail(@PathVariable("review_id") int review_id) {
        Map<String, Object> result = new HashMap<>();
        Optional<Review> rv = reviewRepo.findById(review_id);
        if(rv.isPresent()){
            Review review = rv.get();
            result.put("code", "ok");
            result.put("data", review);
        }
        return result;
    }

    @PostMapping("/review/regist")
    @ResponseBody
    public Map<String, Object> regist(@RequestBody Review review){
        Map<String, Object> result = new HashMap<>();
        Date date = new java.util.Date();
        review.setCreated_at(date);
        reviewRepo.save(review);
        result.put("code", "ok");
        return result;
    }

}
