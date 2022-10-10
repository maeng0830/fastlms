package com.maeng0830.fastlms.course.model;

import com.maeng0830.fastlms.admin.model.CommonParam;
import lombok.Data;

@Data
public class CourseParam extends CommonParam {
    long id; // course id
    long categoryId;

}
