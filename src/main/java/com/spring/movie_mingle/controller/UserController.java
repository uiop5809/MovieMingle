package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.User;
import com.spring.movie_mingle.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            HttpSession session){
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "error");

        Optional<User> member = userRepo.findById(id);
        if(member.isPresent()){
            User user = member.get();
            if (user.getPassword().equals(password)) {
                result.put("code", "ok");
                result.put("message", "로그인 완료");
                session.setAttribute("user", user);
            } else {
                result.put("message", "암호 틀림");
            }
        } else {
            result.put("message", "없거나 삭제된 id");
        }
        System.out.println(result);
        return result;
    }

    // 회원가입
    @PostMapping("/signup")
    @ResponseBody
    public Map<String, Object> signup(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            @RequestParam("name") String name) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("code", "error");

        // 아이디 중복 확인
        Optional<User> existingUser = userRepo.findById(id);
        if (existingUser.isPresent()) {
            result.put("message", "이미 존재하는 아이디");
        } else {
            // 새로운 사용자 생성 및 저장
            User newUser = new User();
            newUser.setId(id);
            newUser.setPassword(password);
            newUser.setName(name);
            userRepo.save(newUser);

            result.put("code", "ok");
            result.put("message", "회원가입 성공");
        }
        System.out.println(result);
        return result;
    }
}
