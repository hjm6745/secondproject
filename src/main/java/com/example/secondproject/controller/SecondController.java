package com.example.secondproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;  // URL 요청(@GetMapping())과 동시에 자동으로 임포트

// 컨트롤러 선언과 동시에 자동으로 임포트
@Controller
public class SecondController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        // model 객체가 "지민" 값을 "username"에 연결해 웹 브라우저로 보냄
        model.addAttribute("username", "지민");
        return "greetings"; // greetings.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "Jimin");
        return "goodbye";
    }
}
