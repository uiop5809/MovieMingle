package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session) {
        Map<String, Object> response = userService.login(email, password, session);
        return ResponseEntity.ok(response);
    }

    /**
     * 회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name) {
        Map<String, Object> response = userService.signup(email, password, name);
        return ResponseEntity.ok(response);
    }
}
