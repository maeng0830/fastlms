package com.maeng0830.fastlms;

// MainPage 클래스의 목적
// 주소와(논리적인주소, 인터넷주소) 물리적인파일(프로그래밍을 위한)을 매핑

// 하나의 주소에 대해서
// 어디서, 누가 매핑해주는가?
// 후보군: 클래스, 속성, 메소드
// 메소드가 적절하다.


import com.maeng0830.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller // 매핑을 위한 클래스
public class MainController {
    private final MailComponents mailComponents;

    @RequestMapping("/")
    public String index() {

        /*String mail = "kmk930830@naver.com";
        String subject = "안녕하세요. 회원 가입 안내 메일입니다.";
        String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";

        mailComponents.sendMail(mail, subject, text);*/
        return "index";
    }

    // request -> web -> server
    // response -> server -> web
    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter printWriter = response.getWriter();

        String msg = "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "</head>"
                + "<body>"
                + "<p>hello</p> <p>fastlms website!!!</p>"
                + "<p>안녕하세요!</p>"
                + "</body>"
                + "</html>";

        printWriter.write(msg);
        printWriter.close();
    }
}
