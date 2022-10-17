package com.maeng0830.fastlms.course.model;

import lombok.Data;

@Data
public class TakeCourseInput {
    long courseId; // course id
    String userId;

    long takeCourseId; // take_course.id

}
