package com.spring.movie_mingle.service;

import com.spring.movie_mingle.domain.User;
import com.spring.movie_mingle.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // 로그인
    public Map<String, Object> login(String email, String password, HttpSession session) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "error");

        User member = userRepository.findByEmail(email);
        if(member != null){
            if (member.getPassword().equals(password)) {
                result.put("code", "ok");
                result.put("message", "로그인 완료");
                session.setAttribute("user", member);
            } else {
                result.put("message", "암호 틀림");
            }
        } else {
            result.put("message", "없거나 삭제된 email");
        }
        return result;
    }

    // 회원가입
    public Map<String, Object> signup(String email, String password, String name) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "error");

        // 이메일 중복 확인
        User member = userRepository.findByEmail(email);
        if (member != null) {
            result.put("message", "이미 존재하는 이메일");
        } else {
            // 새로운 사용자 생성 및 저장
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setName(name);
            userRepository.save(newUser);

            result.put("code", "ok");
            result.put("message", "회원가입 성공");
        }
        return result;
    }

    // 로그아웃
    public Map<String, Object> logout(HttpSession session) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "ok");
        result.put("message", "로그아웃 완료");
        session.invalidate();
        return result;
    }
}
