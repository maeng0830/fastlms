package com.maeng0830.fastlms.admin.controller;

import com.maeng0830.fastlms.admin.service.CategoryService;
import com.maeng0830.fastlms.util.BaseController;
import com.maeng0830.fastlms.course.dto.CourseDto;
import com.maeng0830.fastlms.course.model.CourseInput;
import com.maeng0830.fastlms.course.model.CourseParam;
import com.maeng0830.fastlms.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminCourseController extends BaseController {

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/admin/course/list.do")
    public String list(Model model, CourseParam parameter) {

        parameter.init();

        List<CourseDto> courseList = courseService.list(parameter);


        long totalCount = 0;
        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/course/list";
    }

    // 에러 발생 시 다시 확인할 부분
    @GetMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String add(Model model
            , HttpServletRequest request
            , CourseInput parameter) {

        model.addAttribute("category", categoryService.list());

        boolean editMode = request.getRequestURI().contains("/edit.do");
        CourseDto detail = new CourseDto();

        if (editMode) {
            long id = parameter.getId();

            CourseDto existCourse = courseService.getById(id);

            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌 정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existCourse;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);
        return "admin/course/add";
    }


    @PostMapping(value = {"/admin/course/add.do", "/admin/course/edit.do"})
    public String addSubmit(Model model
            , HttpServletRequest request
            , CourseInput parameter
            , MultipartFile file) {

        String saveFileName = "";
        String urlFileName = "";

        if (file != null) {

            String originalFileName = file.getOriginalFilename();
            String baseLocalPath = "C:\\intellij-project\\fastlms\\files";
            String baseUrlPath = "\\files";

            String[] arrFileName = getNewSaveFile(baseLocalPath, baseUrlPath, originalFileName);

            saveFileName = arrFileName[0];
            urlFileName = arrFileName[1];

            try {
                File newFile = new File(saveFileName);
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }

        parameter.setFileName(saveFileName);
        parameter.setUrlFileName(urlFileName);

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if (editMode) {
            long id = parameter.getId();

            CourseDto existCourse = courseService.getById(id);

            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌 정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = courseService.set(parameter);

        } else {
            boolean result = courseService.add(parameter);
        }

        return "redirect:/admin/course/list.do";
    }

    @PostMapping("/admin/course/delete.do")
    public String del(Model model, HttpServletRequest request, CourseInput parameter) {

        boolean result = courseService.del(parameter.getIdList());

        return "redirect:/admin/course/list.do";
    }
}
