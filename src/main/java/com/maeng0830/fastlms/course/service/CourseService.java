package com.maeng0830.fastlms.course.service;

import com.maeng0830.fastlms.course.dto.CourseDto;
import com.maeng0830.fastlms.course.model.CourseInput;
import com.maeng0830.fastlms.course.model.CourseParam;

import java.util.List;

public interface CourseService {
    // 강좌 등록
    boolean add(CourseInput parameter);

    // 강좌 정보 수정
    boolean set(CourseInput parameter);

    List<CourseDto> list(CourseParam parameter);

    CourseDto getById(long id);

}
