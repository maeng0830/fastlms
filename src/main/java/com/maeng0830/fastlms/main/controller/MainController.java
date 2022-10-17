package com.maeng0830.fastlms.main.controller;

// MainPage 클래스의 목적
// 주소와(논리적인주소, 인터넷주소) 물리적인파일(프로그래밍을 위한)을 매핑

// 하나의 주소에 대해서
// 어디서, 누가 매핑해주는가?
// 후보군: 클래스, 속성, 메소드
// 메소드가 적절하다.


import com.maeng0830.fastlms.admin.dto.BannerDto;
import com.maeng0830.fastlms.admin.model.BannerInput;
import com.maeng0830.fastlms.admin.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RequiredArgsConstructor
@Controller // 매핑을 위한 클래스
public class MainController {
    private final BannerService bannerService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, BannerInput parameter) {
        List<BannerDto> bannerDtoList = bannerService.bannerSlider();
        model.addAttribute("list", bannerDtoList);

        return "index";
    }

    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
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
