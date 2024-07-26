package com.spring.movie_mingle.controller;

import com.spring.movie_mingle.domain.User;
import com.spring.movie_mingle.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    // 로그인기능
    @PostMapping("/user/login")
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
}
