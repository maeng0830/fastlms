package com.maeng0830.fastlms.course.controller;

import com.maeng0830.fastlms.admin.service.CategoryService;
import com.maeng0830.fastlms.common.model.ResponseResult;
import com.maeng0830.fastlms.course.model.ServiceResult;
import com.maeng0830.fastlms.course.model.TakeCourseInput;
import com.maeng0830.fastlms.course.service.CourseService;
import com.maeng0830.fastlms.util.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController // view가 아닌 api(json...)가 리턴된다.
public class ApiCourseController extends BaseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    @PostMapping("/api/course/req.api")
    public ResponseEntity<?> courseReq(Model model
            , @RequestBody TakeCourseInput parameter
            , Principal principal) {

        parameter.setUserId(principal.getName());

        ServiceResult result = courseService.req(parameter);


        if (!result.isResult()) {
            ResponseResult responseResult = new ResponseResult(false, result.getMessage());

            return ResponseEntity.ok().body(responseResult);
        }
        ResponseResult responseResult = new ResponseResult(true);
        return ResponseEntity.ok().body(responseResult);
    }
}
