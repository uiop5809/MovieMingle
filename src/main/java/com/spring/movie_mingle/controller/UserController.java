package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.User;
import com.spring.movie_mingle.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logout(HttpSession session) {
        Map<String, Object> response = userService.logout(session);
        return ResponseEntity.ok(response);
    }

    /**
     * 로그인 상태 확인
     */
    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            response.put("loggedIn", true);
            response.put("user", user);
        } else {
            response.put("loggedIn", false);
        }
        return ResponseEntity.ok(response);
    }


}
