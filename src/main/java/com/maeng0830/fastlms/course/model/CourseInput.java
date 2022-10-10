package com.maeng0830.fastlms.course.model;

import lombok.Data;

@Data
public class CourseInput {
    long id;
    long categoryId;
    String subject;
    String keyWord;
    String summary;
    String contents;
    long price;
    long salePrice;
    String saleEndDtText;

    // 삭제를 위한
    String idList;

}
