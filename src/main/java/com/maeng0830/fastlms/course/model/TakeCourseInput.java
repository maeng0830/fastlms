package com.maeng0830.fastlms.course.model;

import com.maeng0830.fastlms.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeCourseInput {
    long courseId; // course id
    String userId;

}
