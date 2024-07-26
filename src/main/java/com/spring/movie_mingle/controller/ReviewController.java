package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.Review;
import com.spring.movie_mingle.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
