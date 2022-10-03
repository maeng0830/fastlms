package com.maeng0830.fastlms;

// MainPage 클래스의 목적
// 주소와(논리적인주소, 인터넷주소) 물리적인파일(프로그래밍을 위한)을 매핑

// 하나의 주소에 대해서
// 어디서, 누가 매핑해주는가?
// 후보군: 클래스, 속성, 메소드
// 메소드가 적절하다.


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 매핑을 위한 클래스
public class MainPage {
    @RequestMapping("/")
    public String index() {
        return "Index Page";
    }

    @RequestMapping("/hello")
    public String hello() {

        String msg = "hello \r\n fastlms website!!!";
        return msg;
    }
}
