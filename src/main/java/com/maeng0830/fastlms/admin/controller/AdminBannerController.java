package com.maeng0830.fastlms.admin.controller;

import com.maeng0830.fastlms.admin.dto.BannerDto;
import com.maeng0830.fastlms.admin.model.BannerInput;
import com.maeng0830.fastlms.admin.model.BannerParam;
import com.maeng0830.fastlms.admin.service.BannerService;
import com.maeng0830.fastlms.util.BaseController;
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
public class AdminBannerController extends BaseController {

    private final BannerService bannerService;

    // 배너 목록 보기
    @GetMapping("/admin/banner/list.do")
    public String list(Model model, BannerParam parameter) {

        parameter.init();

        List<BannerDto> bannerDtoList = bannerService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(bannerDtoList)) {
            totalCount = bannerDtoList.get(0).getTotalCount();
        }

        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", bannerDtoList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list";
    }

    //
    @GetMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String add(Model model
            , HttpServletRequest request
            , BannerInput parameter) {


        boolean editMode = request.getRequestURI().contains("/edit.do");
        BannerDto detail = new BannerDto();

        if (editMode) {
            long id = parameter.getId();

            BannerDto existBanner = bannerService.getById(id);

            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "배너 정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existBanner;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);
        return "admin/banner/add";
    }

    @PostMapping(value = {"/admin/banner/add.do", "/admin/banner/edit.do"})
    public String addSubmit(Model model
            , HttpServletRequest request
            , BannerInput parameter
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

            BannerDto existBanner = bannerService.getById(id);

            if (existBanner == null) {
                // error 처리
                model.addAttribute("message", "배너 정보가 존재하지 않습니다.");
                return "common/error";
            }

            boolean result = bannerService.set(parameter);

        } else {
            boolean result = bannerService.add(parameter);
        }

        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/admin/banner/delete.do")
    public String del(BannerInput parameter) {

        boolean result = bannerService.del(parameter.getIdList());

        return "redirect:/admin/banner/list.do";
    }
}
