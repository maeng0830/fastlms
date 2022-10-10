package com.maeng0830.fastlms.course.dto;


import com.maeng0830.fastlms.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseDto {

    Long id;
    long categoryId;
    String imagePath;
    String keyWord;
    String subject;
    String summary;
    String contents;
    long price;
    long salePrice;
    LocalDate saleEndDt;
    LocalDateTime regDt;
    LocalDateTime udtDt;

    //추가 컬럼
    long totalCount;
    long seq;

    public static CourseDto of(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .categoryId(course.getCategoryId())
                .imagePath(course.getImagePath())
                .keyWord(course.getKeyWord())
                .subject(course.getSubject())
                .summary(course.getSummary())
                .contents(course.getContents())
                .price(course.getPrice())
                .salePrice(course.getSalePrice())
                .saleEndDt(course.getSaleEndDt())
                .regDt(course.getRegDt())
                .udtDt(course.getUdtDt())
                .build();
    }

    public static List<CourseDto> of(List<Course> courses) {

        if (courses == null) {
            return null;
        }

        List<CourseDto> courseList = new ArrayList<>();
        for (Course x: courses) {
            courseList.add(CourseDto.of(x));
        }
        return courseList;
    }
}
